package springboot.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import springboot.model.Todo;

public class TodoRepositoryImpl {
private static final Logger LOG = LoggerFactory.getLogger(TodoRepository.class);
	
	@Autowired
	private EntityManager entityManager;
	
	public boolean store(Todo todo){
		LOG.debug("Store...");
//		this.entityManager
//			.createNativeQuery("INSERT INTO todo (id,name,priority) VALUES ( "+todo + todo.getName());
		try{
            entityManager.persist(todo);
            return true;
        } catch (Exception e) {
            return false;
        }
	}
	
	@Test
	public List<Todo> getAll(){
		LOG.debug("getAll...");
		List<Todo> result = this.entityManager
				.createNativeQuery("SELECT id, name, priority FROM todo", Todo.class).getResultList();
		return result;
	}
}
