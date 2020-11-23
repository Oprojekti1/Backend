package ohjelmistoprojekti1.kyselylomake.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Kysymys {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long kysid;
	private String kys;
	// private String kystyp;
	private Kysymystyyppi kysymystyyppi;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kysymys")
	private List<Vastaus> vastaukset;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kysymys")
	private List<Vaihtoehto> vaihtoehdot;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "kyselyId")
	private Kysely kysely;

	public Kysymys() {
	} // lisätty RL

//public Kysymys () {
//	super();
//	this.kysid = null;
//	this.radiokys = null;
//
//}
//
//	public Kysymys(String radiokys) {
//		super();
//		this.radiokys = radiokys;
//
//	}
//
//	public Kysymys(Long kysid, String radiokys) {
//		this.kysid = kysid;
//		this.radiokys = radiokys;
//
//	}
	
	public enum Kysymystyyppi {
		radiokysymys,
		avoin
	}


	public Kysymys(Long kysid, String kys, Kysymystyyppi kysymystyyppi, Kysely kysely) {
	super();
	this.kysid = kysid;
	this.kys = kys;
	this.kysymystyyppi = kysymystyyppi;
	this.kysely = kysely;
}

	public Kysymys(String kys, Kysymystyyppi kysymystyyppi, Kysely kysely) {
	super();
	this.kys = kys;
	this.kysymystyyppi = kysymystyyppi;
	this.kysely = kysely;
}

	public Long getKysid() {
		return kysid;
	}
	public void setKysid(Long kysid) {
		this.kysid = kysid;
	}

	

	public String getKys() {
		return kys;
	}

	public void setKys(String kys) {
		this.kys = kys;
	}

	
	public Kysely getKysely() {
		return kysely;
	}

	public void setKysely(Kysely kysely) {
		this.kysely = kysely;
	}

	

	public List<Vastaus> getVastaukset() {
		return vastaukset;
	}

	public void setVastaukset(List<Vastaus> vastaukset) {
		this.vastaukset = vastaukset;
	}
	
	

	public List<Vaihtoehto> getVaihtoehdot() {
		return vaihtoehdot;
	}

	public void setVaihtoehdot(List<Vaihtoehto> vaihtoehdot) {
		this.vaihtoehdot = vaihtoehdot;
	}
	
	public Kysymystyyppi getKysymystyyppi() {
		return kysymystyyppi;
	}
	
	public void setKysymystyyppi(Kysymystyyppi kysymystyyppi) {
		this.kysymystyyppi = kysymystyyppi;
	}

	@Override
	public String toString() {
		if (this.vaihtoehdot != null)
		return "Kysymys [kysid=" + kysid + ", kys=" + kys + ", kysymystyyppi=" + kysymystyyppi + ", kysely=" + this.getKysely() +
				", vaihtoehdot =" + this.getVaihtoehdot() + "]"; 
		else		
		return "Kysymys [kysid=" + kysid + ", kys=" + kys + ", kysymystyyppi=" + kysymystyyppi + ", kysely=" + this.getKysely() +  "]" 
				;
	}

	
	



}
