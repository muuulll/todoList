package kr.or.connect.todo.persistence;

public class TodoSqls {
	
	static final String COUNT_TODO = "SELECT COUNT(*) FROM todo";
	static final String COUNT_TODO_COMPLETED = "SELECT COUNT(*) FROM todo where completed = 1";
	static final String COUNT_TODO_UN_COMPLETED = "SELECT COUNT(*) FROM todo where completed = 0";
	
	static final String SELECT_BY_ID =
			"SELECT id, todo, completed, date FROM todo where id = :id";
	static final String DELETE_BY_ID = "DELETE FROM todo WHERE id= :id";
	static final String UPDATE =
			"UPDATE todo SET todo = :todo, completed = :completed, date = :date WHERE id = :id";

	
}
