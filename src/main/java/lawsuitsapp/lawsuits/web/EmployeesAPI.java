package lawsuitsapp.lawsuits.web;


import lawsuitsapp.lawsuits.async.AsyncEmployeeService;
import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) throws EmployeeNotFoundException {
        return asyncEmployeeService.getEmployeeByIdAsync(id);
    }

    // fixme: treba da bide trgnato, ova odi preku JwtAuthenticationAPI
    @PostMapping
    public void addEmployee(@RequestParam("firstName") String firstName,
                            @RequestParam("lastName") String lastName,
                            @RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("role") String role){
        Employee newEmployee = new Employee(firstName,lastName,username,password,role);
        asyncEmployeeService.addEmployeeAsync(newEmployee);
    }


    // delete na employee gi brise site documents sto toj gi ima kreirano
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") int id) throws EmployeeNotFoundException {
        asyncEmployeeService.deleteEmployeeAsync(id);
    }


    @PutMapping("/{oldId}")
    public void editBasicEmployeeInfo(@PathVariable("oldId") int oldId,
                             @RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("role") String role) throws EmployeeNotFoundException {
        Employee editEmployee = asyncEmployeeService.getEmployeeByIdAsync(oldId);
        editEmployee.setFirstName(firstName);
        editEmployee.setLastName(lastName);
        editEmployee.setRole(role);
        asyncEmployeeService.editEmployeeAsync(oldId,editEmployee);
    }

    @GetMapping("/ofCase/{id}")
    public List<Employee> getAllEmployeesByCaseId(@PathVariable("id") int caseId) throws CaseNotFoundException {
        return asyncEmployeeService.getEmployeesByCaseIdAsync(caseId);
    }


    @GetMapping("/search/{term}")
    public List<Employee> searchEmployees(@PathVariable("term") String term){
        return asyncEmployeeService.searchEmployees(term);
    }
}
