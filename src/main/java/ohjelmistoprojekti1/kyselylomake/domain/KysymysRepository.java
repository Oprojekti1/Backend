package ohjelmistoprojekti1.kyselylomake.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ohjelmistoprojekti1.kyselylomake.domain.Kysymys.Kysymystyyppi;

@Repository
public interface KysymysRepository extends CrudRepository<Kysymys, Long> {
	Kysymys findBykys(String kys);
	Kysymys findByKysid(Long kysid);//tämä lisätty
     Kysymys findByKysymystyyppi(Kysymystyyppi kysymystyyppi);
	

}
