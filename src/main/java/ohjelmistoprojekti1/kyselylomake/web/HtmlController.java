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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ohjelmistoprojekti1.kyselylomake.domain.Kysely;
import ohjelmistoprojekti1.kyselylomake.domain.KyselyRepository;
import ohjelmistoprojekti1.kyselylomake.domain.Kysymys;
import ohjelmistoprojekti1.kyselylomake.domain.KysymysRepository;
import ohjelmistoprojekti1.kyselylomake.domain.Vaihtoehto;
import ohjelmistoprojekti1.kyselylomake.domain.VaihtoehtoRepository;
import ohjelmistoprojekti1.kyselylomake.domain.Vastaus;
import ohjelmistoprojekti1.kyselylomake.domain.VastausRepository;

@Controller
@CrossOrigin
public class HtmlController {

	@Autowired
	private VastausRepository vastausRepository;

	@Autowired
	private KysymysRepository kysrepository;

	@Autowired
	private KyselyRepository kyselyRepository;

	@Autowired
	private VaihtoehtoRepository veRepository;

	@RequestMapping(value = "/vastauslista", method = RequestMethod.GET)
	public String showVastaukset(Model model) {
		List<Vastaus> vastaukset = (List<Vastaus>) vastausRepository.findAll();
		System.out.println(vastaukset);
		model.addAttribute("vastaukset", vastaukset);
		return "vastaus";
	}

	// Kaikki kyselyt
	@RequestMapping("/auth/kysely")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public String KyselyLista(Model model) {
//		List<Vastaus> vastaukset = (List<Vastaus>) vastausRepository.findAll();
//		model.addAttribute("vastaukset", vastaukset);
		model.addAttribute("kyselyt", kyselyRepository.findAll());

		return "kyselyt";
	}

	// Kyselyn lisäys
	@RequestMapping(value = "/auth/add")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public String addKysely(Model model) {
		model.addAttribute("kysely", new Kysely());
		return "addkysely";
	}

	// Kyselyn tallennus
	@RequestMapping(value = "/auth/save", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public String save(@Valid @ModelAttribute Kysely kysely, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("kysely", kysely);
			return "addkysely";
		} else {
			kyselyRepository.save(kysely);
			model.addAttribute("kysely", kysely);
			return "redirect:kysely";
		}

	}

	// Kysymysten lisäys
	@RequestMapping(value = "/auth/addkysymys/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public String addKysymys(@PathVariable("id") Long kyselyId, Model model) {
		Kysely kysely = kyselyRepository.findById(kyselyId).get();
		Kysymys kysymys = new Kysymys();
		kysymys.setKysely(kysely);
		model.addAttribute("kysymys", kysymys);
		return "addkysymys";

	}

	// Kysymysten tallennus
	@RequestMapping(value = "/auth/savekysymys", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public String saveKysymys(@Valid @ModelAttribute Kysymys kysymys, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "addkysymys";
		} else {
			kysrepository.save(kysymys);
			model.addAttribute("kysymys", kysymys);
			return "redirect:/auth/kysely";
		}
	}

	// Kaikki kysymykset
	@RequestMapping(value = "/auth/questions/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public String kyssarit(@PathVariable("id") Long kyselyId, Model model) {
		Kysely kysely = kyselyRepository.findById(kyselyId).get();
		List<Kysymys> kysymykset = kysely.getKysymykset();
		model.addAttribute("kysymykset", kysymykset);
		return "kysymyksetLista";

	}

	// Lisää vaihtoehto
	@RequestMapping(value = "/auth/addvaihto/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public String addVaihtoehto(@PathVariable("id") Long kysid, Model model) {
		Kysymys kysymys = kysrepository.findByKysid(kysid);
		Vaihtoehto vaihtoehto = new Vaihtoehto();
		vaihtoehto.setKysymys(kysymys);
		model.addAttribute("vaihtoehto", vaihtoehto);
		return "addVaihto";

	}

	// Tallenna vaihtoehto
	@RequestMapping(value = "/auth/saveVaihto", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public String saveVaihtoehdot(@Valid @ModelAttribute Vaihtoehto vaihtoehto, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "addVaihto";
		} else {
			veRepository.save(vaihtoehto);
			return "redirect:/auth/allvaihto";
		}
	}

	// Hae kaikki vaihtoehdot
	@RequestMapping(value = "/auth/vaihtikset/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public String vaihtikset(@PathVariable("id") Long kysid, Model model) {
		Kysymys kysymys = kysrepository.findById(kysid).get();
		List<Vaihtoehto> vaihdot = kysymys.getVaihtoehdot();
		model.addAttribute("vaihdot", vaihdot);
		return "vaihtoLista";

	}

	// Kaikki vaihtoehdot
	@RequestMapping(value = "/auth/allvaihto", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public String getAllVaihto(Model model) {
		List<Vaihtoehto> vaihtoehdot = (List<Vaihtoehto>) veRepository.findAll();
		model.addAttribute("vaihtoehdot", vaihtoehdot);
		return "allVaihto";
	}
	
	// Kyselyn poisto, lisätty /auth endpointtiin
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/auth/deletekysely/{id}", method = RequestMethod.GET)
	public String deleteKysely(@PathVariable("id") Long KyselyId, Model model) {
		kyselyRepository.deleteById(KyselyId);
		return "redirect:../kysely";
	}
	
	// Kysymyksen poisto, lisätty /auth endpointtiin
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/auth/deletekysymys/{id}", method = RequestMethod.GET)
	public String deleteKysymys(@PathVariable("id") Long kysid, Model model) {
		kysrepository.deleteById(kysid);
		return "redirect:../kysely";
	}

}
