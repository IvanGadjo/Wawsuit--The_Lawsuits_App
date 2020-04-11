package lawsuitsapp.lawsuits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class LawsuitsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LawsuitsApplication.class, args);
    }

}
