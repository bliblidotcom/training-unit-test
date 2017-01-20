package springboot.model;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;
import springboot.model.constants.TodoPriority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */

@Entity
public class Todo {

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  private Long id;
  private String name;
  private TodoPriority priority;

  public Todo(String name, TodoPriority priority) {
    this.name = name;
    this.priority = priority;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TodoPriority getPriority() {
    return priority;
  }

  public void setPriority(TodoPriority priority) {
    this.priority = priority;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Todo todo = (Todo) o;

    if (!name.equals(todo.name)) return false;
    return priority.equals(todo.priority);

  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + priority.hashCode();
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Todo{");
    sb.append("name='").append(name).append('\'');
    sb.append(", priority=").append(priority);
    sb.append('}');
    return sb.toString();
  }
}
