package springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.model.TodoNew;
import springboot.repository.TodoRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dias on 1/20/2017.
 */
public class TodoServ {

    private static final Logger LOG = LoggerFactory.getLogger(TodoServ.class);

    @Autowired
    private TodoRepo todoRepo;

//    public TodoNew saveTodo(Long id, String name, TodoPriority priority) {
//        LOG.debug("saveTodo...");
//        TodoNew todo = new TodoNew(id, name, priority);
//        return todoRepo.save(todo);
//    }
//
//    public List<TodoNew> getAll() {
//        LOG.debug("getAll...");
//        List<TodoNew> result = todoRepo.findAll();
//        LOG.debug("result:{}", result);
//        return result;
//    }

    public boolean saveTodo(String name, TodoPriority priority) {
        LOG.debug("saveTodo...");
        TodoNew todo = new TodoNew(name, priority);

        return todoRepo.store(todo);
    }

    public List<TodoNew> getAll() {
        LOG.debug("getAll...");
        List<TodoNew> result = todoRepo.getAll();
        LOG.debug("result:{}", result);
        return result;
    }
}
