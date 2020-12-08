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
import ohjelmistoprojekti1.kyselylomake.domain.LinkedService;
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
	
	// Vastaukset kysely id mukaan:
	
//	@RequestMapping(value = "/auth/answersbykysely/{id}", method = RequestMethod.GET)
//	@PreAuthorize("hasAnyAuthority('ADMIN')")
//	public String vastauksetKysely(@PathVariable("id") Long kyselyId, Model model) {
//		Kysely kysely = kyselyRepository.findById(kyselyId).get();
//		List<Vastaus> answers = kysely;
//		model.addAttribute("answers", answers);
//		return "vastauksetbykysely";
//
//	}
	
	// Vastaukset kysymys id mukaan:
	@RequestMapping(value = "/auth/answersbykys/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public String vastauksetkys(@PathVariable("id") Long kysId, Model model) {
		Kysymys kysymys= kysrepository.findById(kysId).get();
		List<Vastaus> answers = kysymys.getVastaukset();
		model.addAttribute("vaihtoehdot", kysymys.getVaihtoehdot());
		model.addAttribute("answers", answers);
		return "vastausbykys";
		
//		Kysymys kysymys= kysrepository.findById(kysId).get();
//		List<Vastaus> answers = kysymys.getVastaukset();
//		 answers = (List<Vastaus>) vastausRepository.findAll();
//		LinkedService.getVastausksienMaara(answers);
//	
//		model.addAttribute("vaihtoehdot", kysymys.getVaihtoehdot());
//		model.addAttribute("answers", answers.size());

	}

	// Kaikki kyselyt
	@RequestMapping("/auth/kysely")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public String KyselyLista(Model model) {
		List<Vastaus> vastaukset = (List<Vastaus>) vastausRepository.findAll();
		LinkedService.getVastausksienMaara(vastaukset);
		model.addAttribute("vastaukset", vastaukset.size());
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
	
	// Vaihtoehtojen poisto
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/auth/deletevaihto/{id}", method = RequestMethod.GET)
	public String deleteVaihto(@PathVariable("id") Long veId, Model model) {
		veRepository.deleteById(veId);
		return "redirect:../kysely";
	}
	
	// Vaihtoehtojen editointi
	
	@RequestMapping(value = "/auth/editvaiht/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public String editVaiht(@PathVariable("id") Long veId, Model model) {
	Vaihtoehto vaihtoehto = veRepository.findById(veId).get();
		model.addAttribute("vaihtoehto", vaihtoehto);
	
		return "editvaihto";
	}
	
	// Vaihtehdon editoinnin tallennus
	
	@RequestMapping(value = "/auth/saveeditvaiht", method = RequestMethod.POST)
	public String saveEditedVaiht(@Valid @ModelAttribute Vaihtoehto vaihtoehto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {	// Jos tulee virheitä
			model.addAttribute("vaihtoehto", vaihtoehto);
		
			return "editvaihto";
		} else {		// Jos kaikki menee oikein
			veRepository.save(vaihtoehto);
			model.addAttribute("vaihtoehto", vaihtoehto);
			
			return "redirect:/auth/kysely";
		}
	}
	
	// Kyselyn poisto, lisätty /auth endpointtiin
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/auth/deletekysely/{id}", method = RequestMethod.GET)
	public String deleteKysely(@PathVariable("id") Long KyselyId, Model model) {
		kyselyRepository.deleteById(KyselyId);
		return "redirect:../kysely";
	}
	
	// Kyselyn muokkaus

	@RequestMapping(value = "/auth/editkysely/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public String editKysely(@PathVariable("id") Long kyselyId, Model model) {
		Kysely kysely = kyselyRepository.findById(kyselyId).get();
		model.addAttribute("kysely", kysely);
		return "editkysely";
	}

	// Kyselyn edit-version tallennus

	@RequestMapping(value = "/auth/saveeditkysely", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public String saveEditedKysely(@Valid @ModelAttribute Kysely kysely, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) { // Jos tulee virheitä
			return "editkysely";
		} else { // Jos kaikki menee oikein
			kyselyRepository.save(kysely);
			model.addAttribute("kysely", kysely);
			return "redirect:kysely";
		}
	}
	
	// Kysymyksen poisto, lisätty /auth endpointtiin
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/auth/deletekysymys/{id}", method = RequestMethod.GET)
	public String deleteKysymys(@PathVariable("id") Long kysid, Model model) {
		kysrepository.deleteById(kysid);
		return "redirect:../kysely";
	}
	// Kysymyksen editointi

		@RequestMapping(value = "/auth/editkys/{id}", method = RequestMethod.GET)
		@PreAuthorize("hasAnyAuthority('ADMIN')")
		public String editKys(@PathVariable("id") Long kysid, Model model) {
		Kysymys kysymys = kysrepository.findById(kysid).get();
			model.addAttribute("kysymys", kysymys);
		
			return "editkys";
		}
		@RequestMapping(value = "/auth/saveeditkys", method = RequestMethod.POST)
		public String saveEditedKys(@Valid @ModelAttribute Kysymys kysymys, BindingResult bindingResult, Model model) {
			if (bindingResult.hasErrors()) {	// Jos tulee virheitä
				model.addAttribute("kysymys", kysymys);
			
				return "editkys";
			} else {		// Jos kaikki menee oikein
				kysrepository.save(kysymys);
				model.addAttribute("kysymys", kysymys);
				
				return "redirect:/auth/kysely";
			}
		}

}
