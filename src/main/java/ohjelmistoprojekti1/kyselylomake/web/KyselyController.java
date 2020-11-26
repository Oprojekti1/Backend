package ohjelmistoprojekti1.kyselylomake.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ohjelmistoprojekti1.kyselylomake.domain.Kysely;
import ohjelmistoprojekti1.kyselylomake.domain.KyselyRepository;
import ohjelmistoprojekti1.kyselylomake.domain.Kysymys;
import ohjelmistoprojekti1.kyselylomake.domain.KysymysRepository;
import ohjelmistoprojekti1.kyselylomake.domain.Vaihtoehto;
import ohjelmistoprojekti1.kyselylomake.domain.VaihtoehtoRepository;

@CrossOrigin
@Controller
public class KyselyController {
	@Autowired
	private KysymysRepository kysrepository;

	@Autowired
	private KyselyRepository kyselyRepository;

	@Autowired
	private VaihtoehtoRepository vrepository;

	// Restful service to get all the questions
	@RequestMapping(value = "/kysymykset", method = RequestMethod.GET)

	public @ResponseBody List<Kysymys> kysListRest() {
		return (List<Kysymys>) kysrepository.findAll();
	}

	// Restful service to get question by id
	@RequestMapping(value = "/kysymykset/{kysid}", method = RequestMethod.GET)

	public @ResponseBody Optional<Kysymys> findKysRest(@PathVariable("kysid") Long kysid) {
		return kysrepository.findById(kysid);
	}

	// Restful service to save a new question
	@RequestMapping(value = "/kysymykset", method = RequestMethod.POST)
	public @ResponseBody Kysymys saveKysRest(@RequestBody Kysymys kysymys) {
		return kysrepository.save(kysymys);
	}

	// Restful service haetaan kysely
	@RequestMapping(value = "/kyselyt", method = RequestMethod.GET)

	public @ResponseBody List<Kysely> kyselyListRest() {
		return (List<Kysely>) kyselyRepository.findAll();
	}

	@RequestMapping(value = "/kyselyt/{kyselyId}", method = RequestMethod.GET)

	public @ResponseBody Optional<Kysely> findKyselyRest(@PathVariable("kyselyId") Long kyselyId) {

		System.out.println(kyselyRepository.findById(kyselyId));
		return kyselyRepository.findById(kyselyId);

	}

}
