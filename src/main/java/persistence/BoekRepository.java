package persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import classes.Boek;

public interface BoekRepository extends CrudRepository<Boek, Long> {
    List<Boek> findByName(String name);

    Boek findById(long id);
}
