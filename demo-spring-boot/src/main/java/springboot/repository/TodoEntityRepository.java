package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springboot.model.Todo;
import springboot.service.TodoEntityRepositoryCustom;
import java.lang.String;
import java.util.List;

@Repository
public interface TodoEntityRepository extends JpaRepository<Todo, Integer>,TodoEntityRepositoryCustom{

	List<Todo> findByName(String name);
	
}
