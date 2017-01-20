package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.model.TodoNew;

/**
 * Created by Dias on 1/20/2017.
 */
public interface TodoRepo extends JpaRepository<TodoNew, Long>, TodoRepoCustom {

}
