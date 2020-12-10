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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Kysymys {
	//automaattisesti generoitu yksilöllinen id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long kysid;
	//luodaan kysymykselle nimi ja määritellään sen pituudelle raja-arvot ja määritellään sen tyypiksi String
	@NotEmpty(message = "Kysymys ei saa olla tyhjä!")
	@Size(min = 4, max = 60, message = "Kysymyksen pitää olla 4-60 merkkiä pitkä!")
	private String kys;
	//luodaan kysymystyyppi joka on tyypiltään ENUM(joka selviää tutkimalla projektia)
	private Kysymystyyppi kysymystyyppi;
	//luodaan tietokantayhdeys taulujen kysymys ja vastaus välille siten että yksi kysymys voi sisältää monta vastausta
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kysymys")
	private List<Vastaus> vastaukset;
	//luodaan tietokantayhdeys taulujen kysymys ja vaihtoehto välille siten että yksi kysymys voi sisältää monta vaihtoehtoa
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kysymys")
	private List<Vaihtoehto> vaihtoehdot;
	//luodaan tietokantayhdeys taulujen kysely ja kysymys välille siten että yksi kysely voi sisältää monta kysymystä
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "kyselyId")
	private Kysely kysely;
	//konstruktorit ja ENUM kysymystyyppi
	public Kysymys() {
	} // lisätty RL

	public enum Kysymystyyppi {
		radio, avoin
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
	//getterit ja setterit
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
		return "Kysymys [kysid=" + kysid + ", kys=" + kys + ", kysymystyyppi=" + kysymystyyppi + ", kysely="
				+ this.getKysely() + "]";
	}

}
