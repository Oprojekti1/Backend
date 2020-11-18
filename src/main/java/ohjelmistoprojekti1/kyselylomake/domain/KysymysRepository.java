package ohjelmistoprojekti1.kyselylomake.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KysymysRepository extends CrudRepository<Kysymys, Long> {
	Kysymys findBykys(String kys);
	Kysymys findByKysid(Long kysid);//tämä lisätty
	Kysymys findBykystyp(String kystyp);
	

}
