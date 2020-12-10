package ohjelmistoprojekti1.kyselylomake.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Vastaus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long vastid;
	private String vast;
	

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "kysid")
	private Kysymys kysymys;

	public Vastaus() {
		super();
	}

	public Vastaus(String vast, Kysymys kysymys) {
		super();
		this.vast = vast;
		this.kysymys = kysymys;
	}

	public Long getVastid() {
		return vastid;
	}

	public void setVastid(Long vastid) {
		this.vastid = vastid;
	}

	public String getVast() {
		return vast;
	}

	public void setVast(String vast) {
		this.vast = vast;
	}

	public Kysymys getKysymys() {
		return kysymys;
	}

	public void setKysymys(Kysymys kysymys) {
		this.kysymys = kysymys;
	}

	@Override
	public String toString() {
		return "Vastaus [vastid=" + vastid + ", vast=" + vast + ", kysymys=" + this.getKysymys() + "]";
	}

}
