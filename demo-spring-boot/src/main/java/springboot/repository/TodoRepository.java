/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springboot.repository;

import org.springframework.data.repository.CrudRepository;
import springboot.model.Todo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sofrie
 */
public interface TodoRepository extends TodoRepositoryCustom, JpaRepository<Todo, Long> {

    List<Todo> findByName(String name);
}
