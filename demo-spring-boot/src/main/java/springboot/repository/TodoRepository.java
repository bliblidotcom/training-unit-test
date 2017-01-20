package springboot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import springboot.model.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
@Service
public interface TodoRepository extends TodoRepositoryCustom, CrudRepository<Todo,Integer> {

    
//  private static final Logger LOG = LoggerFactory.getLogger(TodoRepository.class);
//
//  private final List<Todo> todos = new ArrayList<Todo>();
//
//  public boolean store(Todo todo) {
//    LOG.debug("store...");
//    todos.add(todo);
//
//    return true;
//  }
//
//  public List<Todo> getAll() {
//    LOG.debug("getAll...");
//    List<Todo> result = new ArrayList<Todo>(todos);
//    LOG.debug("result:{}", result);
//    return result;
//  }
}
