package springboot.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springboot.model.Todo;

@Repository
public class TodoH2RepositoryImpl implements TodoH2RepositoryCustom {
	private static final Logger LOG = LoggerFactory.getLogger(TodoH2RepositoryImpl.class);

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Todo> getAll() {
		LOG.debug("GetAll........");
		List<Todo> tudu = entityManager.createNativeQuery("select id, name, priority from TODO;", Todo.class)
				.getResultList();
		LOG.debug("result : {}", tudu);
		return tudu;
	}

	@Override
	public Todo store(Todo todo) {
		String sql = "INSERT INTO TODO(id, name, priority) VALUES(:id, :nama, :prioritas);";
		entityManager.createNativeQuery(sql).setParameter("id", 123).setParameter("nama", todo.getName())
				.setParameter("prioritas", todo.getPriority().ordinal()).executeUpdate();
		return todo;
	}

}
