package lawsuitsapp.lawsuits.web;


import lawsuitsapp.lawsuits.async.AsyncEmployeeService;
import lawsuitsapp.lawsuits.model.Employee;
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

    @PostMapping
    public void addEmployee(@RequestParam("firstName") String firstName,
                            @RequestParam("lastName") String lastName,
                            @RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("role") String role){
        Employee newEmployee = new Employee(firstName,lastName,username,password,role);
        asyncEmployeeService.addEmployeeAsync(newEmployee);
    }

    // fixme!!
    // delete na employee gi brise site documents sto toj gi ima kreirano
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") int id) throws EmployeeNotFoundException {
        asyncEmployeeService.deleteEmployeeAsync(id);
    }


    // edit employee se odnesuva na editiranje na osnovnite podatoci, dodeka pak izmeni vo odnos na dokumentite
    // se pravat so dr requests (addDoc i deleteDoc od DocAPI)
    @PutMapping("/{oldId}")
    public void editEmployee(@PathVariable("oldId") int oldId,
                             @RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("role") String role){
        Employee editEmployee = new Employee(firstName,lastName,username,password,role);
        asyncEmployeeService.editEmployeeAsync(oldId,editEmployee);
    }




    // TODO: OSTANATI
    // -- da ima delete employee no so prefrlanje docs na drug employee
}
