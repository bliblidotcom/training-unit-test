package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.model.Todo;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
