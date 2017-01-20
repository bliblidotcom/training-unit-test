package springboot.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.service.TodoEntityRepositoryCustom;

@Service
public class TodoEntityRepositoryImpl implements TodoEntityRepositoryCustom {

	@Autowired
	EntityManager manager;

	@Override
	public List<Todo> getAll() {
		// TODO Auto-generated method stub
		List<Todo> result = manager.createNativeQuery("select id,name,priority from TODO",Todo.class)
				.getResultList();
		return result;
	}

	@Override
	public boolean saveTodo(String name, TodoPriority priority) {
		try{
			manager.persist(new Todo(name, priority));
			return true;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}
