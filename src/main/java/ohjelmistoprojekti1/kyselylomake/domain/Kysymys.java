package ohjelmistoprojekti1.kyselylomake.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Kysymys {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long kysid;
	private String radiokys;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kysymys")
	private List<Vastaus> vastaukset;

	public Kysymys() {
	} // lisätty RL

//public Kysymys () {
//	super();
//	this.kysid = null;
//	this.radiokys = null;
//
//}

	public Kysymys(String radiokys) {
		super();
		this.radiokys = radiokys;

	}

	public Kysymys(Long kysid, String radiokys) {
		this.kysid = kysid;
		this.radiokys = radiokys;

	}

	public Long getKysid() {
		return kysid;
	}

	public void setKysid(Long kysid) {
		this.kysid = kysid;
	}

	public String getRadiokys() {
		return radiokys;
	}

	public void setRadiokys(String radiokys) {
		this.radiokys = radiokys;
	}

	public List<Vastaus> getVastaukset() {
		return vastaukset;
	}

	public void setVastaukset(List<Vastaus> vastaukset) {
		this.vastaukset = vastaukset;
	}

	@Override
	public String toString() {
		return "Kysymys [kysid=" + kysid + ", radiokys=" + radiokys + "]";
	}

}
