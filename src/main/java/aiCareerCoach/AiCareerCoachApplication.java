package aiCareerCoach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class AiCareerCoachApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiCareerCoachApplication.class, args);
    }

}
