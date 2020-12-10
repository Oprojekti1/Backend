package ohjelmistoprojekti1.kyselylomake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ohjelmistoprojekti1.kyselylomake.domain.Kysely;
import ohjelmistoprojekti1.kyselylomake.domain.KyselyRepository;
import ohjelmistoprojekti1.kyselylomake.domain.Kysymys;
import ohjelmistoprojekti1.kyselylomake.domain.Kysymys.Kysymystyyppi;
import ohjelmistoprojekti1.kyselylomake.domain.KysymysRepository;
import ohjelmistoprojekti1.kyselylomake.domain.User;
import ohjelmistoprojekti1.kyselylomake.domain.UserRepository;
import ohjelmistoprojekti1.kyselylomake.domain.Vaihtoehto;
import ohjelmistoprojekti1.kyselylomake.domain.VaihtoehtoRepository;
import ohjelmistoprojekti1.kyselylomake.domain.Vastaus;
import ohjelmistoprojekti1.kyselylomake.domain.VastausRepository;

@SpringBootApplication
public class KyselylomakeApplication {
	private static final Logger log = LoggerFactory.getLogger(KyselylomakeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KyselylomakeApplication.class, args);
	}
	//command line runner jolla lisätään esimerkkidataa tietokantaan
	@Bean
	public CommandLineRunner demo(KysymysRepository krepository, VastausRepository vastausRepository,
			VaihtoehtoRepository veRepository, KyselyRepository kyselyRepository, UserRepository userrepository) {
		return (args) -> {
			//logitusrivejä bugien metsästyksen avuksi
			log.info("Tallenna kysely");
			//luodaan kyselyrepositorio jonne tallennetaan kyselyitä jotka jokainen voivat sisältäää useita kysymyksiä
			Kysely kysely = new Kysely("Ensinmmäinen", "Tässä kyselään sukupuoli tietoja, harrastuksia ja muita mielenkiinnon kohteita");
			kyselyRepository.save(kysely);
			
			log.info("Tallenna kysymyksiä");
			//luodaan pari kysymystä joista ensimmäisen tyyppi on radiobutton ja toisen avoin vastauskenttä ja tallennetaan ne kysymys repositorioon
			Kysymys k1 = new Kysymys("Mikä on sukupuolesi?", Kysymystyyppi.radio, kysely);
			Kysymys k2 = new Kysymys("Mitä teet vapaa-ajallasi", Kysymystyyppi.avoin, kysely);
			krepository.save(k2);
			krepository.save(k1);
			//Luodaan joitain vastausvaihtoehtoja
			Vastaus v1 = new Vastaus("Mies", k1);
			Vastaus v2 = new Vastaus("Nainen", k1);
			Vastaus v3 = new Vastaus("Muu", k1);
			Vastaus v4 = new Vastaus(" ", k2);
			//tallennetaan luodut vastauskset
			vastausRepository.save(v1);
			vastausRepository.save(v2);
			vastausRepository.save(v3);
			vastausRepository.save(v4);
			//luodaan uusia vastausvaihtoehtoja kysymykselle k1 joka on tyypiltään radiobutton
			Vaihtoehto ve1 = new Vaihtoehto("Mies", k1);
			Vaihtoehto ve2 = new Vaihtoehto("Nainen", k1);
			Vaihtoehto ve3 = new Vaihtoehto("Muu", k1);
			//tallennetaan luodut vaihtoehdot vaihtoehto repositorioon
			veRepository.save(ve1);
			veRepository.save(ve2);
			veRepository.save(ve3);
			//luodaan käyttäjätunnus user
			User u = new User("user", "$2b$10$xqqmzNgMQYMrYfYALdZj.efa8NNYGaSlVN.SepPJLOn6TeD83rElW", "USER"); // salis
			//ja ylläpitäjän tunnus admin																			// user
			User u2 = new User("admin", "$2b$10$x0592PKNYWaHbZOokuJhTebA6eetoxa.iuiZqlfTWSr9smeemiLiK", "ADMIN"); // salis
																													// admin
			//tallennetaan käyttäjä ja admin user repositorioon
			userrepository.save(u);
			userrepository.save(u2);
			//loggausrivejä bugien metsästykseen. jokainen nippu loggaa yksittäisen repositorion sisällön
			log.info("fetch kysymykset");
			for (Kysymys kysymys : krepository.findAll()) {
				log.info(kysymys.toString());
			}

			log.info("fetch vastaukset");
			for (Vastaus vastaus : vastausRepository.findAll()) {
				log.info(vastaus.toString());
			}

			log.info("fetch vaihtoehdot");
			for (Vaihtoehto vaihtoehto : veRepository.findAll()) {
				log.info(vaihtoehto.toString());
			}

			log.info("fetch kysely");
			for (Kysely kysely1 : kyselyRepository.findAll()) {
				log.info(kysely1.toString());
			}

		};
	}

}
