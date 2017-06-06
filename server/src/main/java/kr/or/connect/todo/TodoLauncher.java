package kr.or.connect.todo;

import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;

//DataSource 를 생성
//BookDao에 DataSource를 주입하고 생성
//BookDao.countBooks()를 호출하여 테스트
public class TodoLauncher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		DataSource dataSource = context.getBean(DataSource.class);
		TodoDao dao = new TodoDao(dataSource);
//		TodoDao dao = context.getBean(TodoDao.class);
		
		int count = dao.countTodos();
		Todo todo = dao.selectById(1);
	
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Todo todoInsert = new Todo("네이버 Java", 0, timestamp);
		Integer newId = dao.insert(todoInsert);
		context.close();
	}

}
