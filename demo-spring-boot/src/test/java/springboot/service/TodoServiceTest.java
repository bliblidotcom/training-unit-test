package springboot.service;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import springboot.repository.TodoRepository;

public class TodoServiceTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(TodoServiceTest.class);
	
	/**System under test**/
	 //untuk bikin obj todoservice, akan bikin todoservice, jadi langsing diinject
	@InjectMocks
	private TodoService todoService;

	/**dependency**/
	@Mock
	private TodoRepository todoRepository;
	
	//set up data untuk settingnya
	//before each test method
	@Before
	public void setUp() {
		LOG.debug("setUp...");
		MockitoAnnotations.initMocks(this);
		
	}	
	//Waktu tes udah selesai mau ngapain
	//after each test method
	@After
	public void tearDown(){
		LOG.debug("tearDown...");
		
	}
	
	@Test
	public void getAllTest(){
		LOG.debug("getAllTest..");
		
		//given, when, then merupakan behaviour
		//given
		//when
		//then
		
		this.todoService.getAll();
		
	}
	
	@Test
	public void saveTodoTest(){
		LOG.debug("saveTodoTest..");
		
		this.todoService.saveTodo(null, null);
	}
}
