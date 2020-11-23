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
	private String vaihtoehtoja;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "kysid")
	private Kysymys kysymys;
	
	
	public Vaihtoehto(Long veId, String vaihtoehtoja) {
		super();
		this.veId = veId;
		this.vaihtoehtoja= vaihtoehtoja;
	}


	public Vaihtoehto() {
		super();
	
	}


	public Vaihtoehto(String vaihtoehtoja, Kysymys kysymys) {
		super();
		this.vaihtoehtoja = vaihtoehtoja;
		this.kysymys = kysymys;
	}


	public Long getVeId() {
		return veId;
	}


	public void setVeId(Long veId) {
		this.veId = veId;
	}


	public String getVaihtoehtoja() {
		return vaihtoehtoja;
	}


	public void setVaihtoehtoja(String vaihtoehtoja) {
		this.vaihtoehtoja = vaihtoehtoja;
	}


	public Kysymys getKysymys() {
		return kysymys;
	}


	public void setKysymys(Kysymys kysymys) {
		this.kysymys = kysymys;
	}

	

	@Override
	public String toString() {
		return "Vaihtoehto [veId=" + veId + ", vaihtoehtoja=" + vaihtoehtoja + ", kysymys=" + this.getKysymys() + "]";
	}
	
}
