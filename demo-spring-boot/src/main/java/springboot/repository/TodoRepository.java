package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import springboot.model.Todo;

/**
 * Created by ARDI on 1/20/2017.
 */

@Service
public interface TodoRepository extends JpaRepository<Todo, Long> , TodoRepositoryCustom{
}
