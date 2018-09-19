package dto;


import enums.Status;

import static enums.Status.NOT_DONE;
import static enums.Status.valueOf;

public class Todo {
    private String description;
    private Status status;
    private Integer id;

    public Todo(String description, Status status) {
        this.description = description;
        this.status = status;
    }

    public Todo (String description, Status status, Integer id) {
        this(description, status);
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = valueOf(status);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isCompletable() {
        return status == Status.NOT_DONE;
    }

    @Override
    public String toString() {
        return description + ", " + String.valueOf(status) + ", id" + id;
    }
}