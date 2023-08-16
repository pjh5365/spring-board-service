package pjh5365.springboardservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBoardServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoardServiceApplication.class, args);
    }

}
