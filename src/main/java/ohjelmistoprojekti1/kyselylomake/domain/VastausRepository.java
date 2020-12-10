package ohjelmistoprojekti1.kyselylomake.domain;



import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VastausRepository extends CrudRepository<Vastaus, Long> {
Vastaus findByvast(String vast);
Vastaus findByVastid(Long vastid);//tämä lisätty
//Vastaus findByvast(List<Vastaus> vastaukset);
//Vastaus findByKyseyId(Long kyselyId);




}
