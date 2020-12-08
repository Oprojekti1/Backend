package ohjelmistoprojekti1.kyselylomake.domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public class LinkedService {


	public static  Map<Vastaus, Integer> getVastausksienMaara(List<Vastaus> vastaukset) {

	Map<Vastaus, Integer> tulokset = laskeEsiintymat(vastaukset);
	System.out.println("HALLOOOOOO" + tulokset);
	return tulokset;
	}

	 private static <T> Map<T, Integer> laskeEsiintymat(List<T> list) {
	        Map<T, Integer> tulos = new HashMap<>();
	        for (T object : list) {
	            if (tulos.containsKey(object)) {
	                tulos.put(object, tulos.get(object) + 1);
	            } else {
	                tulos.put(object, 1);
	            }
	        }
	        return tulos;
	    }
}