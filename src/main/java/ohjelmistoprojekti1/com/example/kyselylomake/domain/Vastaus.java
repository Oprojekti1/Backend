package ohjelmistoprojekti1.com.example.kyselylomake.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Vastaus {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String vastaus;
	
	public Vastaus() {}
	
	public Vastaus(String vastaus) {
		super();
		this.vastaus = vastaus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVastaus() {
		return vastaus;
	}

	public void setVastaus(String vastaus) {
		this.vastaus = vastaus;
	}
}
