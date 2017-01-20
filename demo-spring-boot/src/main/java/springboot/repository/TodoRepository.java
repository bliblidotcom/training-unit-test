package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.model.Todo;

/**
 * Created by arielchristianto on 1/20/17.
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryCustom {
    Todo findByName(String name);
}
