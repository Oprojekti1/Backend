package ohjelmistoprojekti1.kyselylomake.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaihtoehtoRepository extends CrudRepository <Vaihtoehto, Long>{
	
	Vaihtoehto findByVaihtoehto(String vaihtoehto);
	Vaihtoehto findByveId(Long veId);

}
