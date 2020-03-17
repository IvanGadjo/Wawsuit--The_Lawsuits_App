package lawsuitsapp.lawsuits.service.jwt;

import java.util.ArrayList;
import java.util.List;

import lawsuitsapp.lawsuits.model.Credentials;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.repository.EmployeeRepo;
import lawsuitsapp.lawsuits.repository.jpa.EmployeeRepoJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/*
The Spring Security Authentication Manager calls this method for getting the user details
from the database when authenticating the user details provided by the user.
 */

@Service
public class JWTUserDetailsService implements UserDetailsService{

    @Autowired
    EmployeeRepoJPA employeeRepoJPA;

    @Autowired
    PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if ("javainuse".equals(username)) {
//            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//                    new ArrayList<>());
//        }
//
//        if ("gadjo".equals(username)){
//            return new User("gadjo", "$2a$10$wAdayXtsPo/MOWgnj29gruyVS.PhRE2pCzSfrTNi409pX8/0VBToK",
//                    new ArrayList<>());
//        }else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
        Employee employee = employeeRepoJPA.findByUsername(username);
        if (employee == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new User(employee.getUsername(),employee.getPassword(),new ArrayList<>());
    }



    public void save(Employee employee){
        String plainPassword = employee.getPassword();
        String encodedPassword = bcryptEncoder.encode(plainPassword);
        employee.setPassword(encodedPassword);
        employeeRepoJPA.save(employee);
    }
}
