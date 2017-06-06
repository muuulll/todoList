package kr.or.connect.todo.persistence;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import kr.or.connect.todo.domain.Todo;

public class TodoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;		// insert
	
	@Bean
	public TodoDao todoDao(DataSource dataSource) {
		return new TodoDao(dataSource);
	}
	
	public TodoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		
		// 테이블명을 todo으로 지정 : SimpleJdbcInsert.withTableName(String) 메서드 이용
		// 자동 생성되는 키 컬럼을 id로 : SimpleJdbcInsert.usingGeneratedKeyColumns(String ...) 이용
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("todo")
				.usingGeneratedKeyColumns("id");
	}
	
	public TodoDao() {
		// TODO Auto-generated constructor stub
	}

	// TodoSqls에 있었던 쿼리를 호출하는 코드
	public int countTodos() {
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.queryForObject(TodoSqls.COUNT_TODO, params, Integer.class);
	}
	
	// 완성 된 일정
	public int countTodosCompleted() {
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.queryForObject(TodoSqls.COUNT_TODO_COMPLETED, params, Integer.class);
	}
	
	// 완성되지 않은 일정
	public int countTodosUnCompleted() {
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.queryForObject(TodoSqls.COUNT_TODO_UN_COMPLETED, params, Integer.class);
	}
	
	// BeanPropertyRowMapper.newInstance()로 생성한 객체 : 멀티스레드에서 접근해도 안전하기 때문에 아래와 같이 멤버 변수로 선언하고 바로 초기화를 해도 무방하다.
	private RowMapper<Todo> rowMapper = BeanPropertyRowMapper.newInstance(Todo.class);

	// BeanPropertyRowMapper
	public Todo selectById(Integer id) {
		RowMapper<Todo> rowMapper = BeanPropertyRowMapper.newInstance(Todo.class);
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return jdbc.queryForObject(TodoSqls.SELECT_BY_ID, params, rowMapper);
	}
	
	// DB에 저장되는 값 : Integer
	public Integer insert(Todo todo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
		return insertAction.executeAndReturnKey(params).intValue();
	}
	
	public int deleteById(Integer id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.update(TodoSqls.DELETE_BY_ID, params);
	}

	public int update(Todo todo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
		return jdbc.update(TodoSqls.UPDATE, params);
	}
}
