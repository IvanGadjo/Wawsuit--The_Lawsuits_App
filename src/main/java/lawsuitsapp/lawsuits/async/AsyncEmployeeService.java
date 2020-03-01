package lawsuitsapp.lawsuits.async;

import lawsuitsapp.lawsuits.dataholder.DataHolder;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.service.EmployeeService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AsyncEmployeeService {

    EmployeeService employeeService;
    DataHolder dataHolder;

    public AsyncEmployeeService(EmployeeService employeeService, DataHolder dataHolder){
        this.employeeService = employeeService;
        this.dataHolder = dataHolder;
    }

    public List<Employee> getAllEmployeesAsync(){
        return employeeService.getAllEmployees();
    }

    //helper:
    public void fillDataBaseAtStart(){
        dataHolder.fillDBAtStart();
    }





    // todo:

    public Employee getEmployeeByIdAsync(int id){
        return null;
    }

    public void addEmployeeAsync(Employee employee){

    }

    public void editEmployeeAsync(Employee employee){

    }

    public void deleteEmployeeAsync(Employee employee){

    }
}
