package ohjelmistoprojekti1.kyselylomake;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ohjelmistoprojekti1.kyselylomake.domain.Kysely;
import ohjelmistoprojekti1.kyselylomake.domain.KyselyRepository;
import ohjelmistoprojekti1.kyselylomake.domain.Kysymys;
import ohjelmistoprojekti1.kyselylomake.domain.Kysymys.Kysymystyyppi;


@ExtendWith(SpringExtension.class)
@DataJpaTest

public class kysymysTests {
	
	@Autowired
	private KyselyRepository krepo;
	
	@Test
	public void kysymysOnLuotavissa() {
		Kysely kysely = new Kysely("Ensinmmäinen");
		krepo.save(kysely);
		Kysymys k1 = new Kysymys("Mikä on sukupuolesi?", Kysymystyyppi.radiokysymys, kysely);
		assertEquals(k1.getKys(), "Mikä on sukupuolesi?");
	}
}
