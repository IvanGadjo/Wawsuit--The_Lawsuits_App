package lawsuitsapp.lawsuits.web;


import lawsuitsapp.lawsuits.async.AsyncEmployeeService;
import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/employees")
public class EmployeesAPI {

    AsyncEmployeeService asyncEmployeeService;

    public EmployeesAPI(AsyncEmployeeService asyncEmployeeService){
        this.asyncEmployeeService = asyncEmployeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployeesFromRepo() throws ExecutionException, InterruptedException {
        //asyncEmployeeService.fillDataBaseAtStart();
        return asyncEmployeeService.getAllEmployeesAsync().get();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) throws EmployeeNotFoundException, ExecutionException, InterruptedException {
        return asyncEmployeeService.getEmployeeByIdAsync(id).get();
    }

// the logic of this method goes through JwtAuthenticationAPI
//    @PostMapping
//    public void addEmployee(@RequestParam("firstName") String firstName,
//                            @RequestParam("lastName") String lastName,
//                            @RequestParam("username") String username,
//                            @RequestParam("password") String password,
//                            @RequestParam("role") String role){
//        Employee newEmployee = new Employee(firstName,lastName,username,password,role);
//        asyncEmployeeService.addEmployeeAsync(newEmployee);
//    }


    // delete na employee gi brise site documents sto toj gi ima kreirano
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") int id) throws EmployeeNotFoundException {
        asyncEmployeeService.deleteEmployeeAsync(id);
    }


    @PutMapping("/{oldId}")
    public void editBasicEmployeeInfo(@PathVariable("oldId") int oldId,
                             @RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("role") String role) throws EmployeeNotFoundException, ExecutionException, InterruptedException {
        Employee editEmployee = asyncEmployeeService.getEmployeeByIdAsync(oldId).get();
        editEmployee.setFirstName(firstName);
        editEmployee.setLastName(lastName);
        editEmployee.setRole(role);
        asyncEmployeeService.editEmployeeAsync(oldId,editEmployee);
    }

    @GetMapping("/ofCase/{id}")
    public List<Employee> getAllEmployeesByCaseId(@PathVariable("id") int caseId) throws CaseNotFoundException, ExecutionException, InterruptedException {
        return asyncEmployeeService.getEmployeesByCaseIdAsync(caseId).get();
    }


    @GetMapping("/search/{term}")
    public List<Employee> searchEmployees(@PathVariable("term") String term) throws ExecutionException, InterruptedException {
        return asyncEmployeeService.searchEmployees(term).get();
    }
}
