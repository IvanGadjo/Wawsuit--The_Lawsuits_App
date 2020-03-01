package lawsuitsapp.lawsuits.service;

import lawsuitsapp.lawsuits.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(int id);

    void addEmployee(Employee employee);

    void editEmployee(Employee employee);

    void deleteEmployee(Employee employee);
}
