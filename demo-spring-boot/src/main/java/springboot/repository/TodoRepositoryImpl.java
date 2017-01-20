package springboot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import springboot.model.Todo;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by isdzulqor on 1/20/17.
 */
public class TodoRepositoryImpl implements TodoRepositoryCustom{

    private static final Logger LOG = LoggerFactory.getLogger(TodoRepositoryImpl.class);

    @Autowired
    EntityManager entityManager;

    @Override
    public boolean store(Todo todo) {
        LOG.debug("store.....");
//        this.entityManager.crea
        return true;
    }

    @Override
    public List<Todo> getAll() {
        LOG.debug("getAll.....");
        List<Todo> todoList = this.entityManager
                .createNativeQuery("SELECT id, name, priority from todo", Todo.class).getResultList();

        LOG.debug("todolist :{}", todoList);

        return todoList;
    }
}
