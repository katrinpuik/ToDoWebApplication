package service;

import dto.Todo;

import java.util.List;
import java.util.stream.Collectors;

public class TodoMapper {

    public List<String> serialize(List<Todo> todoList) {
        return todoList.stream()
                .map(todo -> {
                    String description = todo.getDescription();
                    String status = todo.getStatus() == null ? "" : String.valueOf(todo.getStatus());
                    return description + "," + status;
                })
                .collect(Collectors.toList());
    }

    public List<Todo> deserialize(List<String> stringList) {
        return stringList.stream()
                .map((String row) -> {
                    String[] split = row.split(",", -1);
                    String description = split[0];
                    String status = split[1].isEmpty() ? null : split[1];
                    return createTodo(description, status);
                })
                .collect(Collectors.toList());
    }

    private Todo createTodo(String description, String status) {
        Todo todo = new Todo(description);
        todo.setStatus(status);
        return todo;
    }
}
