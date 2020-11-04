package ohjelmistoprojekti1.kyselylomake.domain;

public class Kysymys {
private Long id;
private String radiokys;

public Kysymys () {
	this.id = null;
	this.radiokys = null;

}

public Kysymys (String radiokys) {
	this.radiokys = radiokys;
	
}

public Kysymys (Long id, String radiokys) {
	this.id = id;
	this.radiokys = radiokys;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getRadiokys() {
	return radiokys;
}

public void setRadiokys(String radiokys) {
	this.radiokys = radiokys;
}

@Override
public String toString() {
	return "Kysymys [id=" + id + ", radiokys=" + radiokys + "]";
}




}
