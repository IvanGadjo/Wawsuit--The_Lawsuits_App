package lawsuitsapp.lawsuits.web;


import lawsuitsapp.lawsuits.async.AsyncEmployeeService;
import lawsuitsapp.lawsuits.model.Employee;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/employees")
public class EmployeesAPI {

    AsyncEmployeeService asyncEmployeeService;

    public EmployeesAPI(AsyncEmployeeService asyncEmployeeService){
        this.asyncEmployeeService = asyncEmployeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployeesFromRepo(){
        asyncEmployeeService.fillDataBaseAtStart();
        return asyncEmployeeService.getAllEmployeesAsync();
    }
}
