package ohjelmistoprojekti1.com.example.kyselylomake;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KyselylomakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KyselylomakeApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner kyselyDemo() {
		return (args) -> {
			
		};
	}

}
