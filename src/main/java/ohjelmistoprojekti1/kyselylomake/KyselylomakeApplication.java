package ohjelmistoprojekti1.kyselylomake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ohjelmistoprojekti1.kyselylomake.domain.Kysymys;
import ohjelmistoprojekti1.kyselylomake.domain.KysymysRepository;
import ohjelmistoprojekti1.kyselylomake.domain.Vastaus;
import ohjelmistoprojekti1.kyselylomake.domain.VastausRepository;



@SpringBootApplication
public class KyselylomakeApplication {
	private static final Logger log = LoggerFactory.getLogger(KyselylomakeApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(KyselylomakeApplication.class, args);
	}
	
	 @Bean
	public CommandLineRunner demo(KysymysRepository krepository, VastausRepository vrepository) {
		return (args) -> {
			log.info("Tallenna kysymyksiä");
			Kysymys k1 = new Kysymys("Mikä on sukupuolesi?");
			
      		krepository.save(k1);
			
			 Vastaus v1 = new Vastaus("Mies", k1);
			 Vastaus v2 = new Vastaus("Nainen", k1);
			Vastaus v3 = new Vastaus("Muu", k1);
			
			vrepository.save(v1);
			vrepository.save(v2);
     		vrepository.save(v3);
			
			log.info("fetch kysymykset");
			for (Kysymys kysymys : krepository.findAll()) {
				log.info(kysymys.toString());
			}
			
			
		};
	}

}
