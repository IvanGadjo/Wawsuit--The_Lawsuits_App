package lawsuitsapp.lawsuits.repository;

import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeRepo {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(int id) throws EmployeeNotFoundException;

    void addEmployee(Employee employee);

    void deleteEmployee(int id) throws EmployeeNotFoundException;

}
