package ohjelmistoprojekti1.kyselylomake.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ohjelmistoprojekti1.kyselylomake.domain.Kysymys;
import ohjelmistoprojekti1.kyselylomake.domain.KysymysRepository;
import ohjelmistoprojekti1.kyselylomake.domain.Vastaus;
import ohjelmistoprojekti1.kyselylomake.domain.VastausRepository;

@CrossOrigin
@Controller
public class VastausController {
	
	@Autowired
	private VastausRepository vastausRepository;
	
    @Autowired
    private KysymysRepository kysrepository;
	
	  @RequestMapping(value="/vastaukset", method = RequestMethod.GET)
      public @ResponseBody List<Vastaus> vasListRest() {   
          return (List<Vastaus>) vastausRepository.findAll();
      }
	  
	// Restful service to get question by id
    @RequestMapping(value="/vastaukset/{vastid}", method = RequestMethod.GET)
    public @ResponseBody Optional<Vastaus> findKysRest(@PathVariable("vastid") Long vastid) {	
    	return vastausRepository.findById(vastid);
    }  
    
	 // Restful service to save a new question
    @RequestMapping(value="/vastaus/{kysid}", method = RequestMethod.POST)
    public @ResponseBody Vastaus saveVastRest(@PathVariable("kysid") Long kysid, @RequestBody Vastaus vastaus) {	
    	Kysymys kysymys = kysrepository.findById(kysid).get();
    	vastaus.setKysymys(kysymys);
    	return vastausRepository.save(vastaus);
    }
}
