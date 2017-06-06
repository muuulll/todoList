package kr.or.connect.todo.api;

import java.sql.Timestamp;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.service.TodoService;

@RestController
@RequestMapping("/api/todos")	// /api/todos
public class TodoController {
	private TodoService service;
	
	@Autowired
	public TodoController(TodoService service) {
		this.service = service;
	}
	
	// post -> todo, date = 받아오는 값
	// post -> completed = 0
	// 완료 버튼 누를 경우 completed = 1
	// id : 기본 증가값
//	@PostMapping()
//	@ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<Todo> post(@RequestBody Todo mTodo) {
//
//		Todo todo = new Todo();
//		todo.setTodo(mTodo.getTodo());
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        todo.setDate(timestamp);
//        todo.setCompleted(0);
//
//        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
//    }
	
	//Insert Action
 	@PostMapping("/new-todo")
 	@ResponseStatus(HttpStatus.CREATED)
 	Todo create(@RequestBody Todo todo){
 		service.create(todo); 
 		return todo;
	 }
	
	@GetMapping()
	Collection<Todo> readList() {
		return service.findAll();
	}
	
	@GetMapping("/hello")
	String hello(){
		return "hello!!!!!";
	}
	
	@GetMapping("/{id}")
	Todo read(@PathVariable  Integer id) {
		return service.findById(id);
	}
	
}
