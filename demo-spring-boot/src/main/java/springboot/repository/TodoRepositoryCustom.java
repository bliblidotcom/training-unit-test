/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springboot.repository;

import springboot.model.Todo;
import java.util.List;

/**
 *
 * @author Marlina
 */
public interface TodoRepositoryCustom {
    public boolean store(Todo todo);
    public List<Todo> getAll();
}
