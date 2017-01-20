package springboot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import springboot.model.Todo;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by The Frost on 20/01/2017.
 */
public class TodoRepositoryImpl implements TodoRepositoryCustom{

    private static final Logger LOG = LoggerFactory.getLogger(TodoRepository.class);

    @Autowired
    private EntityManager entityManager;

    public boolean store(Todo todo) {
        LOG.debug("store...");
        //this.entityManager.createNativeQuery("INSERT INTO todo(id, name, priority) values(" + todo.getId() +"," + todo.getName() + "," + todo.getPriority().ordinal() + ")").executeUpdate();
        try{
            entityManager.persist(todo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Todo> getAll() {
        LOG.debug("getAll...");
        List<Todo> result = this.entityManager.createNativeQuery("SELECT id, name, priority from todo", Todo.class).getResultList();
        return result;
    }
}

