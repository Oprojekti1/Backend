package ohjelmistoprojekti1.kyselylomake.domain;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VastausRepository extends CrudRepository<Vastaus, Long> {
Vastaus findByvast(String vast);
Vastaus findByVastid(Long vastid);//tämä lisätty



}
