package ohjelmistoprojekti1.kyselylomake.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KyselyRepository extends CrudRepository<Kysely, Long> {
	Kysely findByKyselyId(Long kyselyId);
	Kysely findByNimi (String nimi);

}
