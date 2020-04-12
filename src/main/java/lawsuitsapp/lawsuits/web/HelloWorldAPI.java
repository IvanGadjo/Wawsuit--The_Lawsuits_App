package lawsuitsapp.lawsuits.web;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/hello")
public class HelloWorldAPI {        // fixme: This is only a test API for the login functionalities


    public HelloWorldAPI(){

    }

    @GetMapping
    public String firstPage(){
        return "Hello WoOoOorld!";
    }
}
