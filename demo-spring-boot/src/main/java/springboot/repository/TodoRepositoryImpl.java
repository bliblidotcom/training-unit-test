package springboot.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import springboot.model.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


public class TodoRepositoryImpl implements TodoRepositoryCustom {
	
	@Autowired
	//private JdbcTemplate jdbctemplate;
	private EntityManager entitymanager;
	
	private static final Logger LOG = LoggerFactory.getLogger(TodoRepository.class);

	private final List<Todo> todos = new ArrayList<Todo>();

	public boolean store(Todo todo) {
		LOG.debug("store...");
		todos.add(todo);
		//this.entitymanager.createNativeQuery(arg0)
		return true;
	}

	public List<Todo> getAll() {
		LOG.debug("getAll...");
		//List<Todo> result = new ArrayList<Todo>(todos);
		//List<Todo> result = this.jdbctemplate.queryForList("select name, priority from todo", Todo.class);
		List<Todo> result = this.entitymanager
				.createNativeQuery("select id, name, priority from todo", Todo.class)
				.getResultList();
		LOG.debug("result:{}", result);
		return result;
	}
}
