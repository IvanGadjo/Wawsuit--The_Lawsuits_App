package lawsuitsapp.lawsuits.repository.impl;

import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.repository.EmployeeRepo;
import lawsuitsapp.lawsuits.repository.EmployeeRepoJPA;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepoImpl implements EmployeeRepo {

    EmployeeRepoJPA employeeRepoJPA;

    public EmployeeRepoImpl(EmployeeRepoJPA employeeRepoJPA){
        this.employeeRepoJPA = employeeRepoJPA;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepoJPA.findAll();
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
