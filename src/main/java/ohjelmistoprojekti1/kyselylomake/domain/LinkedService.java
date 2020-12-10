package ohjelmistoprojekti1.kyselylomake.domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public class LinkedService {


	public static  Map<String, Integer> getVastausksienMaara(List<Vastaus> vastaukset) {
		
	

	Map<String, Integer> tulokset = laskeEsiintymat(vastaukset); // Key = Vastaus, Integer = value
	
	System.out.println("HALLOOOOOO" + " " + tulokset.values().size());
	return tulokset;
	}

	 private static  Map<String, Integer> laskeEsiintymat(List<Vastaus> list) {  // The T type indicates that it can refer to any type
	        Map<String, Integer> tulos = new HashMap<>();
	        for (Vastaus vastaus : list) {
	            if (tulos.containsKey(vastaus.getVast())) {	//Containskey =  Returns true if this map contains a mapping for the specified key. // vaihda get kyselyid
	                tulos.put(vastaus.getVast(), tulos.get(vastaus.getVast()) + 1);
	            } else {
	                tulos.put(vastaus.getVast(), 1);
	            }
	        }
	        return tulos;
	    }
	 

	
}