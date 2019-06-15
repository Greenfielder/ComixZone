package ru.comixzone.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.comixzone.entities.Comix;

/**
 * Created by Mickey on 15.06.2019.
 */

@Repository
public interface ComixRepository extends CrudRepository<Comix, Long> {
}
