package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.model.Todo;
import java.util.List;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */

public interface TodoRepository extends TodoRepositoryCustom, JpaRepository<Todo,Long> {
  List<Todo> findById (Long id);
}
