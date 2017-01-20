package springboot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springboot.model.Todo;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Juan on 1/20/17.
 */
@Repository
public class TodoRepositoryImpl implements TodoRepositoryCustom{
    private static final Logger LOG = LoggerFactory.getLogger(TodoRepositoryImpl.class);

    @Autowired
    private EntityManager entityManager;

    //implementasi bila pake custome todoRepository
    @Override
    public boolean store(Todo todo) {
//        todo.setId();
        Long id = todo.getId();
        //this.entityManager.createNativeQuery("insert into todo(id, name, priority) values (" + todo.getId() + ", '" + todo.getName() + "', " + todo.getPriority().ordinal() + ")").executeUpdate();
        try{
            entityManager.persist(todo);
            return true;

        }catch (Exception e){
            return false;
        }

    }

    @Override
    public List<Todo> getAll() {
        LOG.debug("getAll....");
        List<Todo> result = this.entityManager.createNativeQuery("SELECT id, name, priority from todo", Todo.class).getResultList();
        LOG.debug("result:{} " , result);
        return result;
    }

}
