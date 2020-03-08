package lawsuitsapp.lawsuits.service.impl;

import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.model.exceptions.DocumentNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;
import lawsuitsapp.lawsuits.repository.DocumentsRepo;
import lawsuitsapp.lawsuits.repository.EmployeeRepo;
import lawsuitsapp.lawsuits.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepo employeeRepo;
    DocumentsRepo documentsRepo;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo, DocumentsRepo documentsRepo){
        this.employeeRepo = employeeRepo;
        this.documentsRepo = documentsRepo;
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

    @Override
    public void deleteEmployee(int id) throws EmployeeNotFoundException {

        Employee employee = employeeRepo.getEmployeeById(id);
        employee.getDocuments().stream().forEach(d -> {
            try {
                documentsRepo.deleteDocument(d.getID());
            } catch (DocumentNotFoundException e) {
                e.printStackTrace();
            }
        });

        employeeRepo.deleteEmployee(id);
    }



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
