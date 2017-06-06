package kr.or.connect.todo.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import kr.or.connect.todo.domain.Todo;

@Service
public class TodoService {

	private ConcurrentMap<Integer, Todo> repo = new ConcurrentHashMap<>();
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	private AtomicInteger maxId = new AtomicInteger(0);

	public Todo findById(Integer id) {
		return repo.get(id);
	}

//	public Collection<Todo> findAll() {
//		return repo.values();
//	}

	public Todo create(Todo todo) {
		Integer id = maxId.addAndGet(1);
		todo.setId(id);
		repo.put(id, todo);
		return todo;
	}

//	public Todo findById(Integer id){
//		return new Todo(1, "스터디", 0, timestamp);
//	}
//	
	public Collection<Todo> findAll() {
//		return repo.values();
		
		return Arrays.asList(
				new Todo(1, "스터디", 1, timestamp),
				new Todo(2, "제출", 1,  timestamp)
			);
	}
	
}
