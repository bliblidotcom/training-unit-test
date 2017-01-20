package springboot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import springboot.model.Todo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
@Service
public interface TodoRepository extends TodoRepositoryCustom, JpaRepository<Todo, Long> {

}
