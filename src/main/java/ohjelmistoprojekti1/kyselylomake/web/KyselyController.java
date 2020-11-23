package ohjelmistoprojekti1.kyselylomake.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public @ResponseBody Optional<Kysely> findKyselyRest(@PathVariable("kyselyId") Long kyselyId){
		
		System.out.println( kyselyRepository.findById(kyselyId));
		return  kyselyRepository.findById(kyselyId);
	
	}
	
	@RequestMapping("/kysely")
	public String KyselyLista(Model model) {
	     model.addAttribute("kyselyt", kyselyRepository.findAll());
		
		return "kyselyt";
	}
	
	@RequestMapping(value = "/add")
	public String addKysely(Model model) {
		model.addAttribute("kysely", new Kysely());
		// model.addAttribute("kysymykset", kysrepository.findAll());
		return "addkysely";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Kysely kysely) {
		kyselyRepository.save(kysely);
		return "redirect:/kysely";
	}
	
	@RequestMapping(value = "/addkysymys/{id}", method = RequestMethod.GET)
	public String addKysymys(@PathVariable("id") Long kyselyId, Model model) {
		Kysely kysely = kyselyRepository.findById(kyselyId).get();
		Kysymys kysymys = new Kysymys();
		kysymys.setKysely(kysely);
		model.addAttribute("kysymys", kysymys);
		model.addAttribute("vaihtoehdot", vrepository.findAll());
		return "addkysymys";
		
	}
	
	@RequestMapping(value= "/savekysymys", method = RequestMethod.POST)
	public String saveKysymys(@ModelAttribute Kysymys kysymys, Model model) {
		model.addAttribute("kysymys", kysymys);
		model.addAttribute("vaihtoehdot", vrepository.findAll());
		return "redirect:/kysely";
	}

}
