package lawsuitsapp.lawsuits.service.impl;

import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;
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

    @Override
    public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
        return employeeRepo.getEmployeeById(id);
    }

    @Override
    public void addEmployee(Employee newEmployee) {
        employeeRepo.addEmployee(newEmployee);
    }


    @Override
    public void editEmployee(int oldId, Employee editedEmployee) {
        employeeRepo.editEmployee(oldId,editedEmployee);
    }


    @Override
    public void deleteEmployee(int id) throws EmployeeNotFoundException {
        employeeRepo.deleteEmployee(id);
    }




    // todo:

    @Override
    public void addDocumentToEmployee(Employee employee, Document docToAdd) {

    }
}
