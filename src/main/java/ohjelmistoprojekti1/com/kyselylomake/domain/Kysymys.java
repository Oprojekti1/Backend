package ohjelmistoprojekti1.com.kyselylomake.domain;

public class Kysymys {

	private String teksti;
	
	
	public Kysymys (String teksti) {
		this.teksti = teksti;
	}

	public String getTeksti() {
		return teksti;
	}

	public void setTeksti(String teksti) {
		this.teksti = teksti;
	}

	@Override
	public String toString() {
		return "Kysymys [teksti=" + teksti + "]";
	}
	
	
}
