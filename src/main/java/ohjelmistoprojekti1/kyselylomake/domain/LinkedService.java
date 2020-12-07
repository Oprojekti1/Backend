package ohjelmistoprojekti1.kyselylomake.domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public class LinkedService {
	@Autowired
	private static VastausRepository vastausRepository;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Vastaus> vastaukset = new LinkedList<>((List<Vastaus>) vastausRepository.findAll());
		
		for (int i = 0; i < vastaukset.size(); i ++) {
			System.out.println("HALLOOOOOO" + vastaukset.get(i));
		}
	
	}

}