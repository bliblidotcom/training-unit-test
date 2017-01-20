/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springboot.repository;

import java.util.List;
import springboot.model.Todo;

/**
 *
 * @author Sofrie
 */
public interface TodoRepositoryCustom {
    public List<Todo> getAll();
    public boolean store(Todo todo);
}
