package ohjelmistoprojekti1.kyselylomake.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ohjelmistoprojekti1.kyselylomake.domain.Kysely;
import ohjelmistoprojekti1.kyselylomake.domain.Kysymys;
import ohjelmistoprojekti1.kyselylomake.domain.KysymysRepository;
import ohjelmistoprojekti1.kyselylomake.domain.Vaihtoehto;
import ohjelmistoprojekti1.kyselylomake.domain.VaihtoehtoRepository;
import ohjelmistoprojekti1.kyselylomake.domain.Vastaus;

@CrossOrigin
@Controller
public class VaihtoehtoController { // Tässä osiossa löytyy vaihtoehtojen rest endpointit
	@Autowired
	private VaihtoehtoRepository veRepository;

	@Autowired
	private KysymysRepository kysrepository;

	// Haetaan kaikki vaihtoehdot
	@RequestMapping(value = "/vaihtoehdot", method = RequestMethod.GET)
	public @ResponseBody List<Vaihtoehto> vahListRest() {
		return (List<Vaihtoehto>) veRepository.findAll();
	}

	// Haetaan vaihtoehdot id perusteella
	@RequestMapping(value = "/vaihtoehdot/{veId}", method = RequestMethod.GET)
	public @ResponseBody Vaihtoehto findVastRest(@PathVariable("veId") Long veId) {
		return veRepository.findByveId(veId);
	}

	// Haetaan vaihtoehdot kysymys id perusteella
	@RequestMapping(value = "/vaihtoehto/{kysid}", method = RequestMethod.POST)
	public @ResponseBody Vaihtoehto saveVaihtRest(@PathVariable("kysid") Long kysid,
			@RequestBody Vaihtoehto vaihtoehto) {
		Kysymys kysymys = kysrepository.findByKysid(kysid);
		vaihtoehto.setKysymys(kysymys);
		return veRepository.save(vaihtoehto);
	}

}
