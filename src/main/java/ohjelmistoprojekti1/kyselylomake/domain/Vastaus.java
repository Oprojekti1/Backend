package ohjelmistoprojekti1.kyselylomake.domain;

public class Vastaus {
private Long id;
private String radiovast;



public Vastaus() {
	super();
}



public Vastaus(String radiovast) {
	super();
	this.radiovast = radiovast;
}



public Vastaus(Long id, String radiovast) {
	super();
	this.id = id;
	this.radiovast = radiovast;
}



public Long getId() {
	return id;
}



public void setId(Long id) {
	this.id = id;
}



public String getRadiovast() {
	return radiovast;
}



public void setRadiovast(String radiovast) {
	this.radiovast = radiovast;
}



@Override
public String toString() {
	return "Vastaus [id=" + id + ", radiovast=" + radiovast + "]";
}



}
