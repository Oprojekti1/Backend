package ohjelmistoprojekti1.kyselylomake.domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public class LinkedService {

// Tässä haetaan kaikki vastaukset
	public static  Map<String, Integer> getKaikkiVastaukset(List<Vastaus> vastaukset) {
		
	Map<String, Integer> tulokset = laskeKaikkiVastaukset(vastaukset); // Key = Vastaus, Integer = value
	
	System.out.println("HALLOOOOOO" + " " + tulokset.values().size());
	return tulokset;
	}

	 private static  Map<String, Integer> laskeKaikkiVastaukset(List<Vastaus> list) {  // The T type indicates that it can refer to any type
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
	 
 // Tähän tulee kysely id mukaan
	 
		public static  Map<String, Integer> getVastauksetKyselynIdMukaan(List<Vastaus> vastauksetKysely) {
			
			Map<String, Integer> tulokset1 = vastauksetByKyselyId(vastauksetKysely); // Key = Vastaus, Integer = value
			
			System.out.println("TOIMISITKO JOOKOS KOOKOS" + " " + tulokset1.size());
			tulokset1.size();
			return tulokset1;
			}

			 private static  Map<String, Integer> vastauksetByKyselyId(List<Vastaus> lista) {  // The T type indicates that it can refer to any type
			        Map<String, Integer> tulos1 = new HashMap<>();
			        for (Vastaus vastaus : lista) {
			            if (tulos1.containsKey(vastaus.getKysymys().getKysely().getKyselyId().toString())) {	//Containskey =  Returns true if this map contains a mapping for the specified key. 
			                tulos1.put(vastaus.getKysymys().getKysely().getKyselyId().toString(), tulos1.get(vastaus.getKysymys().getKysely().getKyselyId().toString()) + 1);
			            } else {
			                tulos1.put(vastaus.getKysymys().getKysely().getKyselyId().toString(), 1);
			            }
			        }
			        return tulos1;
			    }

	 // Tähän tulee kysymys id mukaan
			 
				public static  Map<String, Integer> getVastauksetKysIdMukaan(List<Vastaus> vastauksetKys) {
					
					Map<String, Integer> tulokset2 = vastauksetByKysId(vastauksetKys); // Key = Vastaus, Integer = value
					
					System.out.println("PLIIIIIIS GOOOOOOOOOOOD" + " " + tulokset2.size());
					return tulokset2;
					}

					 private static  Map<String, Integer> vastauksetByKysId(List<Vastaus> listat) {  // The T type indicates that it can refer to any type
					        Map<String, Integer> tulos2 = new HashMap<>();
					        for (Vastaus vastaus : listat) {
					            if (tulos2.containsKey(vastaus.getKysymys().getKysid().toString())) {	//Containskey =  Returns true if this map contains a mapping for the specified key. 
					                tulos2.put(vastaus.getKysymys().getKysid().toString(), tulos2.get(vastaus.getKysymys().getKysid().toString()) + 1);
					            } else {
					                tulos2.put(vastaus.getKysymys().getKysid().toString(), 1);
					            }
					        }
					        
					
					        return tulos2;
					    }
			 
	
}