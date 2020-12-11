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

// kyselyluokka jossa luodaan useita kysymyksiä sisältävä kysely
@Entity
public class Kysely {

	// automaattisesti generoitu yksilöivä tunnus jokaiselle kyselylle
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long kyselyId;

	// luodaan kyselylle nimi ja määritellään sen pituudelle raja-arvot ja
	// määritellään sen tyypiksi String
	@NotEmpty(message = "Nimi ei saa olla tyhjä!")
	@Size(min = 4, max = 30, message = "Nimen pitää olla 4-30 merkkiä pitkä!")
	private String nimi;

	@NotEmpty(message = "Kuvaus ei saa olla tyhjä!")
	@Size(min = 4, max = 120, message = "Kuvauksen pitää olla 4-30 merkkiä pitkä!")
	private String intro;

	// luodaan kyselyyn lista kysymyksistä, tai siis luodaan tietokantayhteys joka
	// yhdistää useita kysymyksiä sisältävän listan oskasi kyselyä
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kysely")
	private List<Kysymys> kysymykset;

	// Tarvittavat getterit ja setterit
	public Kysely(Long kyselyId, String nimi, String intro) {
		super();
		this.kyselyId = kyselyId;
		this.nimi = nimi;
		this.intro = intro;
	}

	public Kysely(String nimi, String intro) {
		super();
		this.nimi = nimi;
		this.intro = intro;
	}

	public Kysely() {
		super();

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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public List<Kysymys> getKysymykset() {
		return kysymykset;
	}

	public void setKysymykset(List<Kysymys> kysymykset) {
		this.kysymykset = kysymykset;
	}

	@Override
	public String toString() {
		return "Kysely [kyselyId=" + kyselyId + ", nimi=" + nimi + ", intro=" + intro + "]";
	}

}
