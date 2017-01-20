package springboot.Repository;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import springboot.Configuration;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;

/**
 * Created by Fransiskus A K on 20/01/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional(readOnly = false)
@EnableAutoConfiguration
@SpringBootTest

public class TodoRepositoryTest {

    private static final Logger LOG = LoggerFactory.getLogger(TodoRepositoryTest.class);

    @Autowired
    TodoRepository todoRepository;


    @Test
    public  void  findRepoTest() {

        //definisikan d data utk tod1
        Todo tod1 = new Todo("pertama", TodoPriority.MEDIUM);

        // nilai data awal yang tersimpan dihitung (sebelum ditambahi)
        long hitungAwal = todoRepository.count();

        //ditambahi data lagi
        todoRepository.save(tod1);

        //jumlah data akhir yang tersimpan dihitung
        long hitungAkhir = todoRepository.count();

        //jumlah data akhir harus = data awal+1
        Assert.assertThat(hitungAkhir, Matchers.equalTo(hitungAwal + 1));



    }
    @Test
    public void savingTest() {
        //definisikan isi dari data todo
        Todo todo = new Todo("Mencoba proses saving" , TodoPriority.HIGH);
                LOG.info("todo : {}", todo.toString());


        //masukkan todo ke savedTodo

        Todo savedTodo = todoRepository.save(todo);

        //tampilkan hasil saving yang ada di dalam savedtodo
        LOG.info("savedTodo: {}", savedTodo.toString() ) ;

        //pastikan isi dari savedtodo dan todo(yg belum dimasukkan) harus sama
        Assert.assertThat(savedTodo, Matchers.equalTo(todo));

    }

}

