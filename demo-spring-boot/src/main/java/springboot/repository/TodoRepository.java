package springboot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
@Service
public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryCustom {
//
//  private static final Logger LOG = LoggerFactory.getLogger(TodoRepository.class);
//  private final List<Todo> todos = new ArrayList<Todo>();
//  public boolean store(Todo todo);
//  public List<Todo> getAll();

  List<Todo> findById(Long id);
  List<Todo> findByName(String name);
  List<Todo> findByPriority(TodoPriority priority);
}
