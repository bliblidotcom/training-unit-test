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
 * @author ALz
 */
public interface TodoRepository extends CrudRepository<Todo, Long>,TodoRepositoryCustom{
    
}
