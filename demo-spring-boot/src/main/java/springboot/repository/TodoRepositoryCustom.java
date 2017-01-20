package springboot.repository;

import springboot.model.Todo;

import java.util.List;

/**
 * Created by Juan on 1/20/17.
 */
public interface TodoRepositoryCustom {

    //interface bila ingin custom repository
//    public boolean store(Todo todo);
    public List<Todo> getAll();
}
