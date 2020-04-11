package lawsuitsapp.lawsuits.async;

import lawsuitsapp.lawsuits.dataholder.DataHolder;
import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;
import lawsuitsapp.lawsuits.service.EmployeeService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncEmployeeService {

    EmployeeService employeeService;
    DataHolder dataHolder;

    public AsyncEmployeeService(EmployeeService employeeService, DataHolder dataHolder){
        this.employeeService = employeeService;
        this.dataHolder = dataHolder;
    }

    @Async
    public CompletableFuture<List<Employee>> getAllEmployeesAsync(){
        return CompletableFuture.completedFuture(employeeService.getAllEmployees());
    }

    @Async
    public CompletableFuture<Employee> getEmployeeByIdAsync(int id) throws EmployeeNotFoundException {
        return CompletableFuture.completedFuture(employeeService.getEmployeeById(id));
    }

    @Async
    public void addEmployeeAsync(Employee newEmployee){
        employeeService.addEmployee(newEmployee);
    }

    //helper:
    public void fillDataBaseAtStart(){
        dataHolder.fillDBAtStart();
    }

    @Async
    public void editEmployeeAsync(int oldId, Employee editedEmployee) throws EmployeeNotFoundException {
        employeeService.editEmployee(oldId,editedEmployee);
    }

    @Async
    public void deleteEmployeeAsync(int id) throws EmployeeNotFoundException {
        employeeService.deleteEmployee(id);
    }

    @Async
    public CompletableFuture<List<Employee>> getEmployeesByCaseIdAsync(int caseId) throws CaseNotFoundException {
        return CompletableFuture.completedFuture(employeeService.getEmployeesByCaseId(caseId));
    }

    @Async
    public CompletableFuture<List<Employee>> searchEmployees(String term){
        return CompletableFuture.completedFuture(employeeService.searchEmployees(term));
    }
}
