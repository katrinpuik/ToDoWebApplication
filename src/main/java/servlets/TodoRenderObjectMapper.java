package servlets;

import dto.Todo;
import dto.TodoRenderObject;

class TodoRenderObjectMapper {

    static TodoRenderObject map(Todo todo, String url, Boolean expanded) {
        return new TodoRenderObject(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getLabels(),
                todo.getStatus(),
                todo.getCreationDate(),
                todo.getDoneDate(),
                todo.getUpdateDate(),
                todo.getDueDate(),
                url,
                expanded
        );
    }
}