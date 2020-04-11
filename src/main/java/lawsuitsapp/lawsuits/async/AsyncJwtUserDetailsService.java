package lawsuitsapp.lawsuits.async;

import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.service.jwt.JWTUserDetailsService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncJwtUserDetailsService {

    JWTUserDetailsService jwtUserDetailsService;

    public AsyncJwtUserDetailsService(JWTUserDetailsService jwtUserDetailsService){
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Async
    public CompletableFuture<UserDetails> loadUserByUsernameAsync(String username){
        return CompletableFuture.completedFuture(jwtUserDetailsService.loadUserByUsername(username));
    }

    @Async
    public void saveUserAsync(Employee employee){
        jwtUserDetailsService.save(employee);
    }
}
