package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.model.Todo;

public interface TodoH2Repository extends JpaRepository<Todo, Long>, TodoH2RepositoryCustom  {

	
}
