package lawsuitsapp.lawsuits.repository.impl;

import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;
import lawsuitsapp.lawsuits.repository.EmployeeRepo;
import lawsuitsapp.lawsuits.repository.jpa.DocumentsRepoJPA;
import lawsuitsapp.lawsuits.repository.jpa.EmployeeRepoJPA;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepoImpl implements EmployeeRepo {

    EmployeeRepoJPA employeeRepoJPA;
    DocumentsRepoJPA documentsRepoJPA;

    public EmployeeRepoImpl(EmployeeRepoJPA employeeRepoJPA, DocumentsRepoJPA documentsRepoJPA){
        this.employeeRepoJPA = employeeRepoJPA;
        this.documentsRepoJPA = documentsRepoJPA;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepoJPA.findAll();
    }


    @Override
    public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
        return employeeRepoJPA.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public void addEmployee(Employee newEmployee) {
        employeeRepoJPA.save(newEmployee);
    }

    @Override
    public Employee getEmployeeByUsername(String username) {
        return employeeRepoJPA.findByUsername(username);
    }

    @Override
    public List<Employee> searchEmployees(String term) {
        return employeeRepoJPA.searchEmployees(term);
    }

    @Override
    public void deleteEmployee(int id) throws EmployeeNotFoundException {

        Employee empToDelete = getEmployeeById(id);
        employeeRepoJPA.delete(empToDelete);


    }





}
