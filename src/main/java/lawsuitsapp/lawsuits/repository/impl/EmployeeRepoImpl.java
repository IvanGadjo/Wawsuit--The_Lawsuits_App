package lawsuitsapp.lawsuits.repository.impl;

import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;
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


    @Override
    public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
        return employeeRepoJPA.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public void addEmployee(Employee newEmployee) {
        employeeRepoJPA.save(newEmployee);
    }

    @Override
    public void editEmployee(int oldId, Employee editedEmployee) {

        // mozebi ova treba da go smenis da bide kako vo prethodnata verzija na lawsuits app
        Employee old = employeeRepoJPA.getOne(oldId);

        employeeRepoJPA.delete(old);

        employeeRepoJPA.save(editedEmployee);
    }


    // todo:

    @Override
    public void deleteEmployee(Employee employee) {

    }

    @Override
    public void addDocumentToEmployee(Employee employee, Document docToAdd) {

    }
}
