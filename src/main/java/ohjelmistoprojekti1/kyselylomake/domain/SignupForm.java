package ohjelmistoprojekti1.kyselylomake.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//tämäluokka määrittää kirjautumislomakkeen ja sille syötettävissä olevat tiedot ja niiden reunaehdot
public class SignupForm {

	// esimerkiksi käyttäjänimi on vähintään 4 merkkiä ja enintään 30 merkkiä pitkä.
	// näytetään virheilmoitus jos syöte on väärä
	@NotEmpty(message = "Käyttjänimi ei saa olla tyhjä!")
	@Size(min = 4, max = 30, message = "Käyttäjänimen pitää olla 4-30 merkkiä pitkä!")
	private String username = "";

	@NotEmpty(message = "Salasana ei saa olla tyhjä!")
	@Size(min = 4, max = 30, message = "Salasanan pitää olla 4-30 merkkiä pitkä!")
	private String password = "";

	@NotEmpty(message = "Salasana ei saa olla tyhjä!")
	@Size(min = 4, max = 30, message = "Salasanan pitää olla 4-30 merkkiä pitkä!")
	private String passwordCheck = "";

	@NotEmpty
	private String role = "ADMIN"; // määritetty että aina kun rekisteröityy on rooli automaattisesti ylläpitäjä

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
