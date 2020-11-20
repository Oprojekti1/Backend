package ohjelmistoprojekti1.kyselylomake;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ohjelmistoprojekti1.kyselylomake.domain.Kysymys;

@ExtendWith(SpringExtension.class)
@DataJpaTest

public class kysymysTests {
	
	@Test
	public void kysymysOnLuotavissa() {
		Kysymys kysy1 = new Kysymys("mitä kuuluu?");
		assertEquals(kysy1.getRadiokys(), "mitä kuuluu?");
	}


}
