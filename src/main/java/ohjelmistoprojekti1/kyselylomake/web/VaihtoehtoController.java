package ohjelmistoprojekti1.kyselylomake.web;

import java.util.List;

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
import ohjelmistoprojekti1.kyselylomake.domain.Kysymys;
import ohjelmistoprojekti1.kyselylomake.domain.KysymysRepository;
import ohjelmistoprojekti1.kyselylomake.domain.Vaihtoehto;
import ohjelmistoprojekti1.kyselylomake.domain.VaihtoehtoRepository;
import ohjelmistoprojekti1.kyselylomake.domain.Vastaus;

@CrossOrigin
@Controller
public class VaihtoehtoController {
	@Autowired
	private VaihtoehtoRepository veRepository;

	@Autowired
	private KysymysRepository kysrepository;

	// Restful service to get all options
	@RequestMapping(value = "/vaihtoehdot", method = RequestMethod.GET)
	public @ResponseBody List<Vaihtoehto> vahListRest() {
		return (List<Vaihtoehto>) veRepository.findAll();
	}

	// Restful service to get options by id
	@RequestMapping(value = "/vaihtoehdot/{veId}", method = RequestMethod.GET)
	public @ResponseBody Vaihtoehto findVastRest(@PathVariable("veId") Long veId) {
		return veRepository.findByveId(veId);
	}

	// Restful service to save a new option
	@RequestMapping(value = "/vaihtoehto/{kysid}", method = RequestMethod.POST)
	public @ResponseBody Vaihtoehto saveVaihtRest(@PathVariable("kysid") Long kysid,
			@RequestBody Vaihtoehto vaihtoehto) {
		Kysymys kysymys = kysrepository.findByKysid(kysid);
		vaihtoehto.setKysymys(kysymys);
		return veRepository.save(vaihtoehto);
	}

	// ThymeLeaf:

	@RequestMapping(value = "/auth/addvaihto/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public String addKysymys(@PathVariable("id") Long kysid, Model model) {
		Kysymys kysymys = kysrepository.findByKysid(kysid);
		Vaihtoehto vaihto = new Vaihtoehto();
		vaihto.setKysymys(kysymys);
		model.addAttribute("vaihto", vaihto);

		// model.addAttribute("vaihtoehdot", vrepository.findAll());
		return "addVaihto";

	}

	@RequestMapping(value = "/auth/saveVaihto", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public String saveVaihtoehdot(@ModelAttribute Vaihtoehto vaihtoehtoja, Model model) {
		veRepository.save(vaihtoehtoja);
		return "redirect:/vaihtoehdot";
	}

	@RequestMapping(value = "/auth/vaihtikset/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public String vaihtikset(@PathVariable("id") Long kysid, Model model) {
		Kysymys kysymys = kysrepository.findById(kysid).get();

		List<Vaihtoehto> vaihdot = kysymys.getVaihtoehdot();

		model.addAttribute("vaihdot", vaihdot);

		return "vaihtoLista";

	}
}
