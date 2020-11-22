package ohjelmistoprojekti1.kyselylomake.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Vaihtoehto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long veId;
	private String vaihtoehto;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "kysid")
	private Kysymys kysymys;
	
	
	public Vaihtoehto(Long veId, String vaihtoehto) {
		super();
		this.veId = veId;
		this.vaihtoehto = vaihtoehto;
	}


	public Vaihtoehto() {
		super();
	
	}


	public Vaihtoehto(String vaihtoehto, Kysymys kysymys) {
		super();
		this.vaihtoehto = vaihtoehto;
		this.kysymys = kysymys;
	}


	public Long getVeId() {
		return veId;
	}


	public void setVeId(Long veId) {
		this.veId = veId;
	}


	public String getVaihtoehto() {
		return vaihtoehto;
	}


	public void setVaihtoehto(String vaihtoehto) {
		this.vaihtoehto = vaihtoehto;
	}


	public Kysymys getKysymys() {
		return kysymys;
	}


	public void setKysymys(Kysymys kysymys) {
		this.kysymys = kysymys;
	}

	

	@Override
	public String toString() {
		return "Vaihtoehto [veId=" + veId + ", vaihtoehto=" + vaihtoehto + ", kysymys=" + this.getKysymys() + "]";
	}
	
}
