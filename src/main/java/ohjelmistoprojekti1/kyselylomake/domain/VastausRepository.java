package ohjelmistoprojekti1.kyselylomake.domain;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VastausRepository extends CrudRepository<Vastaus, Long> {
Vastaus findByradiovast(String radiovast);
Vastaus findByVastid(Long vastid);//tämä lisätty



}
