/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springboot.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import springboot.model.Todo;

/**
 *
 * @author Amesa
 */
public interface TodoRepositoryCustom {
    public boolean store(Todo todo);
    public List<Todo> getAll();
}
