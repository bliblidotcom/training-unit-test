package springboot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import springboot.model.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
@Service
public class TodoRepositoryImpl implements TodoRepositoryCustom{

  private static final Logger LOG = LoggerFactory.getLogger(TodoRepositoryImpl.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public boolean store(Todo todo) {
    LOG.debug("store...");
    //todos.add(todo);

    return true;
  }

  public List<Todo> getAll() {
    LOG.debug("getAll...");
    List<Todo> result = null;
    LOG.debug("result:{}", result);

    this.jdbcTemplate.queryForList("");

    return result;
  }
}
