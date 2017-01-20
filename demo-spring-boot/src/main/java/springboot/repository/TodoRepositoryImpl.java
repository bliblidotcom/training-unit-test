package springboot.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springboot.model.Todo;

@Repository
public class TodoRepositoryImpl implements TodoRepositoryCustom{
	private static final Logger LOG = LoggerFactory.getLogger(TodoRepositoryImpl.class);
	private final List<Todo> list = new ArrayList<Todo>();
	
	@Autowired
	private EntityManager em;
	
	@Override
	public boolean store(Todo todo) {		
		try{
			this.em.persist(todo);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public List<Todo> getAll() {
		List result = this.em.createNativeQuery("select * from todo", Todo.class).getResultList();
		return result;
	}

}
