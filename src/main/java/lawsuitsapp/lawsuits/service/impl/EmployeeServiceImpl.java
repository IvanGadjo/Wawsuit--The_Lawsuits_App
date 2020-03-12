package lawsuitsapp.lawsuits.service.impl;

import lawsuitsapp.lawsuits.model.Case;
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

import java.util.ArrayList;
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


        // remove the employee from all the cases that the emp is working on
        List<Integer> oneEmployeeList = new ArrayList<>();
        oneEmployeeList.add(id);

        casesService.getCasesByEmployeeId(id).stream().forEach(c ->{
            try {
                casesService.removeEmployeesFromCase(c.getID(),oneEmployeeList);
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

    @Override
    public void removeCaseFromEmployee(int employeeId, int caseId) throws EmployeeNotFoundException, CaseNotFoundException {
        Employee employee = getEmployeeById(employeeId);
        Case theCase = casesService.getCaseById(caseId);

        // print methods for debugging
        //theCase.getEmployees().forEach(e -> System.out.println("emps in case:"+e.getID()));
        //employee.getCases().forEach(c -> System.out.println("cases of emp:"+c.getID()));

        employee.removeCase(theCase);

        // print method for debugging
        //employee.getCases().forEach(c -> System.out.println("cases of emp AR:"+c.getID()));

        // save the employee
        addEmployee(employee);
    }

    @Override
    public void addCaseToEmployee(int employeeId, int caseId) throws EmployeeNotFoundException, CaseNotFoundException {
        Employee employee = getEmployeeById(employeeId);
        Case theCase = casesService.getCaseById(caseId);
        employee.addCase(theCase);

        // save the employee
        addEmployee(employee);
    }

    @Override
    public List<Employee> getEmployeesByCaseId(int caseId) throws CaseNotFoundException {
        Case theCase = casesService.getCaseById(caseId);

        return theCase.getEmployees();
    }
}
