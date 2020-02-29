package lawsuitsapp.lawsuits.web;


// todo: Ova treba da bide koe bilo drugo api sto ne e /login, koe e zastiteno so proverka dali userot ima token pred da go access

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/hello")
public class HelloWorldAPI {


    public HelloWorldAPI(){

    }

    @GetMapping
    public String firstPage(){
        return "Hello WoOoOorld!";
    }
}
