package springboot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;
import springboot.model.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
@Service
public interface TodoRepository extends JpaRepository<Todo, Long>,TodoRepositoryCustom {
//	List<Todo> findByLastname(String name);
//	List<Todo> findById(long ID);
// 
}
