package springboot.model;

import springboot.model.constants.TodoPriority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */

@Entity
public class Todo {

  @Id
  @GeneratedValue
  private Long id;

  private String name;
  private TodoPriority priority;

  public Todo(Long id,String name, TodoPriority priority) {
    this.id=id;
    this.name = name;
    this.priority = priority;

  }

  public Todo(){}

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

  public Long getId() {return id;}

  public void setId(Long id) {this.id = id;}

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
