package lawsuitsapp.lawsuits.web;

import lawsuitsapp.lawsuits.async.AsyncEmployeeService;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;
import lawsuitsapp.lawsuits.service.jwt.JWTUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import lawsuitsapp.lawsuits.config.JwtTokenUtil;
import lawsuitsapp.lawsuits.model.auth.JwtRequest;
import lawsuitsapp.lawsuits.model.auth.JwtResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping(path = "/login")
public class JwtAuthenticationAPI {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JWTUserDetailsService userDetailsService;

    @Autowired
    private AsyncEmployeeService asyncEmployeeService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public void addEmployee(@RequestParam("firstName") String firstName,
                            @RequestParam("lastName") String lastName,
                            @RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("role") String role){
        Employee newEmployee = new Employee(firstName,lastName,username,password,role);
        userDetailsService.save(newEmployee);
    }

    @PutMapping("/changeCredentials/{id}")
    public ResponseEntity<?> changeCredentialsOfEmployee(@PathVariable("id") int id,
                                            @RequestParam("username") String newUsername,
                                            @RequestParam("password") String newPassword) throws EmployeeNotFoundException {
        Employee employee = asyncEmployeeService.getEmployeeByIdAsync(id);
        employee.setUsername(newUsername);
        employee.setPassword(newPassword);

        userDetailsService.save(employee);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(newUsername);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/confirmPassword")
    public boolean confirmPassword(@RequestParam("username")String username,
                                @RequestParam("password") String password){
        boolean success = true;
        try {
            authenticate(username,password);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
