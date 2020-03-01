package lawsuitsapp.lawsuits.service.jwt;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/*
The Spring Security Authentication Manager calls this method for getting the user details
from the database when authenticating the user details provided by the user.
 */

@Service
public class JWTUserDetailsService implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("javainuse".equals(username)) {
            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        }
//        else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }

        if ("gadjo".equals(username)){
            return new User("gadjo", "$2a$10$wAdayXtsPo/MOWgnj29gruyVS.PhRE2pCzSfrTNi409pX8/0VBToK",
                    new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
