package service;

import dto.ToDo;

import java.util.List;
import java.util.stream.Collectors;

public class ToDoMapper {

    public List<String> serialize(List<ToDo> toDoList) {
        return toDoList.stream()
                .map(toDo -> {
                    String description = toDo.getDescription();
                    String status = toDo.getStatus() == null ? "" : String.valueOf(toDo.getStatus());
                    return description + "," + status;
                })
                .collect(Collectors.toList());
    }

    public List<ToDo> deserialize(List<String> stringList) {
        return stringList.stream()
                .map((String row) -> {
                    String[] split = row.split(",", -1);
                    String description = split[0];
                    String status = split[1].isEmpty() ? null : split[1];
                    return createToDo(description, status);
                })
                .collect(Collectors.toList());
    }

    private ToDo createToDo(String description, String status) {
        ToDo toDo = new ToDo(description);
        toDo.setStatus(status);
        return toDo;
    }
}
