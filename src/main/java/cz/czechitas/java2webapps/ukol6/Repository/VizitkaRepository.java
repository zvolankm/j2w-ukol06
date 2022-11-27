package cz.czechitas.java2webapps.ukol6.Repository;
import cz.czechitas.java2webapps.ukol6.Entity.Vizitka;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VizitkaRepository extends CrudRepository<Vizitka, Integer> {
}
