package springboot.repository;

import springboot.model.TodoNew;

import java.util.List;

/**
 * Created by Dias on 1/20/2017.
 */
public interface TodoRepoCustom {

    boolean store(TodoNew todo);
    List<TodoNew> getAll();
}
