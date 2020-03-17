package lawsuitsapp.lawsuits.service;

import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(int id) throws EmployeeNotFoundException;

    void addEmployee(Employee employee);

    void editEmployee(int oldId, Employee editedEmployee) throws EmployeeNotFoundException;

    void deleteEmployee(int id) throws EmployeeNotFoundException;

    void removeCaseFromEmployee(int employeeId, int caseId) throws EmployeeNotFoundException, CaseNotFoundException;

    void addCaseToEmployee(int employeeId, int caseId) throws EmployeeNotFoundException, CaseNotFoundException;

    List<Employee> getEmployeesByCaseId(int caseId) throws CaseNotFoundException;

    Employee getEmployeeByUsername(String username);

}
