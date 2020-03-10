package lawsuitsapp.lawsuits.service.impl;

import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.DocumentNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;
import lawsuitsapp.lawsuits.repository.DocumentsRepo;
import lawsuitsapp.lawsuits.repository.EmployeeRepo;
import lawsuitsapp.lawsuits.service.CasesService;
import lawsuitsapp.lawsuits.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepo employeeRepo;
    // todo: change to service
    DocumentsRepo documentsRepo;
    CasesService casesService;



    public EmployeeServiceImpl(EmployeeRepo employeeRepo, DocumentsRepo documentsRepo, CasesService casesService){
        this.employeeRepo = employeeRepo;
        this.documentsRepo = documentsRepo;
        this.casesService = casesService;
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
        return employeeRepo.getEmployeeById(id);
    }

    @Override
    public void addEmployee(Employee newEmployee) {
        employeeRepo.addEmployee(newEmployee);
    }


    // fixme: sredi sto se desava so cases,createdCases
    @Override
    public void deleteEmployee(int id) throws EmployeeNotFoundException {

        Employee employee = employeeRepo.getEmployeeById(id);

        // delete all docs
        employee.getDocuments().stream().forEach(d -> {
            try {
                documentsRepo.deleteDocument(d.getID());
            } catch (DocumentNotFoundException e) {
                e.printStackTrace();
            }
        });

        // set createdBy in all created cases to null
        employee.getCreatedCases().stream().forEach(cc ->{
            try {
                casesService.setEmployeeCreatorToNull(cc.getID());
            } catch (CaseNotFoundException e) {
                e.printStackTrace();
            }
        });

        employeeRepo.deleteEmployee(id);
    }


    // fixme: OK
    @Override
    public void editEmployee(int oldId, Employee editedEmployee) throws EmployeeNotFoundException {
        Employee oldEmployee = employeeRepo.getEmployeeById(oldId);

        oldEmployee.setFirstName(editedEmployee.getFirstName());
        oldEmployee.setLastName(editedEmployee.getLastName());
        oldEmployee.setUsername(editedEmployee.getUsername());
        oldEmployee.setPassword(editedEmployee.getPassword());
        oldEmployee.setRole(editedEmployee.getRole());

        employeeRepo.addEmployee(oldEmployee);
    }

}
