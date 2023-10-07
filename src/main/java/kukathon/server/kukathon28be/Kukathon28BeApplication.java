package kukathon.server.kukathon28be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class Kukathon28BeApplication {

    public static void main(String[] args) {
        SpringApplication.run(Kukathon28BeApplication.class, args);
    }

}
