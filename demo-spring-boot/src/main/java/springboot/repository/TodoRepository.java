package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import springboot.model.Todo;

import java.util.List;

/**
 * Created by Ryan Bagus Susilo on 1/20/2017.
 */
public interface TodoRepository extends JpaRepository<Todo,Long>,TodoRepositoryCustom{

}
