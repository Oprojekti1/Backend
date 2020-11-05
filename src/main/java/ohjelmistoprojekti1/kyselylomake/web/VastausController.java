package ohjelmistoprojekti1.kyselylomake.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import ohjelmistoprojekti1.kyselylomake.domain.Vastaus;
import ohjelmistoprojekti1.kyselylomake.domain.VastausRepository;

@CrossOrigin
@Controller
public class VastausController {
	
	@Autowired
	private VastausRepository vastausRepository;
	

	// Restful service to get question by id
    @RequestMapping(value="/vastauskset/{vastid}", method = RequestMethod.GET)
    public @ResponseBody Optional<Vastaus> findKysRest(@PathVariable("vastid") Long vastid) {	
    	return vastausRepository.findById(vastid);
    }  
}
