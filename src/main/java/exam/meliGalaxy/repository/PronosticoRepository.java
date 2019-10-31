package exam.meliGalaxy.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import exam.meliGalaxy.model.Clima;
import exam.meliGalaxy.model.Pronostico;

public interface PronosticoRepository extends CrudRepository<Pronostico, Integer> {
	
	List<Optional<Pronostico>> findByClima(Clima clima);
	
	Integer countByClima (Clima clima);

}
