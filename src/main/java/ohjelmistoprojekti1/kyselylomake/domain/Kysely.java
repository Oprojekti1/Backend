package ohjelmistoprojekti1.kyselylomake.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Kysely {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long kyselyId;

	@NotEmpty(message = "Nimi ei saa olla tyhjä!")
	@Size(min = 4, max = 30, message = "Nimen pitää olla 4-30 merkkiä pitkä!")
	private String nimi;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kysely")
	private List<Kysymys> kysymykset;

	public Kysely(Long kyselyId, String nimi) {
		super();
		this.kyselyId = kyselyId;
		this.nimi = nimi;
	}

	public Kysely(String nimi) {
		super();
		this.nimi = nimi;
	}

	public Kysely() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getKyselyId() {
		return kyselyId;
	}

	public void setKyselyId(Long kyselyId) {
		this.kyselyId = kyselyId;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public List<Kysymys> getKysymykset() {
		return kysymykset;
	}

	public void setKysymykset(List<Kysymys> kysymykset) {
		this.kysymykset = kysymykset;
	}

	@Override
	public String toString() {
		return "Kysely [kyselyId=" + kyselyId + ", nimi=" + nimi + "]";
	}

}
