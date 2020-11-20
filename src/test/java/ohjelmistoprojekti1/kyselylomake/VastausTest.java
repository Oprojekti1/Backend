package ohjelmistoprojekti1.kyselylomake;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import ohjelmistoprojekti1.kyselylomake.domain.Kysymys;
import ohjelmistoprojekti1.kyselylomake.domain.Vastaus;
//import ohjelmistoprojekti1.kyselylomake.domain.VastausRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest


public class VastausTest {
	
	//jos  testataan tallentumista repositorioon tämä otetaan käyttöön.
	//@Autowired
	//private VastausRepository vastausRepository;
	
	@Test
	public void vastausOnLuotavissa() {
		Kysymys k1 = new Kysymys("Mikä on sukupuolesi?");
		Vastaus v2 = new Vastaus("Mies", k1);
		assertThat(v2.getRadiovast().contains("Mies"));
	}
	
	
}
