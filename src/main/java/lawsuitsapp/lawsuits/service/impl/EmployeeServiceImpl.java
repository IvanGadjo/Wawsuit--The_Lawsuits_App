package lawsuitsapp.lawsuits.service.impl;

import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.repository.EmployeeRepo;
import lawsuitsapp.lawsuits.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepo employeeRepo;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo){
        this.employeeRepo = employeeRepo;
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.getAllEmployees();
    }


    // todo:
    @Override
    public Employee getEmployeeById(int id) {
        return null;
    }

    @Override
    public void addEmployee(Employee employee) {

    }

    @Override
    public void editEmployee(Employee employee) {

    }

    @Override
    public void deleteEmployee(Employee employee) {

    }
}
