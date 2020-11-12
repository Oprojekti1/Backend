package ohjelmistoprojekti1.kyselylomake.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ohjelmistoprojekti1.kyselylomake.domain.Kysymys;
import ohjelmistoprojekti1.kyselylomake.domain.KysymysRepository;
import ohjelmistoprojekti1.kyselylomake.domain.Vastaus;
import ohjelmistoprojekti1.kyselylomake.domain.VastausRepository;

@Controller
@CrossOrigin
public class HtmlController {
	
	@Autowired
	private VastausRepository vastausRepository;

	@Autowired
	private KysymysRepository kysrepository;
	
	@RequestMapping(value = "/vastauslista", method = RequestMethod.GET)
	public String showVastaukset(Model model) {
		List<Vastaus> vastaukset = (List<Vastaus>) vastausRepository.findAll();
		System.out.println(vastaukset);
		model.addAttribute("vastaukset", vastaukset);
		return "vastaus";
	}
	

	

}
