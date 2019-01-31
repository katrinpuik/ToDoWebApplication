package dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import enums.Status;

import java.util.Date;

import static enums.Status.valueOf;

public class Todo {
    private String description;
    private Status status;
    private Integer id;
    private Date dueDate;

    public Todo(String description, Status status) {
        this.description = description;
        this.status = status;
    }

    public Todo (String description, Status status, Integer id, Date dueDate) {
        this(description, status);
        this.id = id;
        this.dueDate = dueDate;
    }

    public Todo (String description, Status status, Date dueDate) {
        this(description, status);
        this.dueDate = dueDate;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return description + ", " + String.valueOf(status) + ", id" + id;
    }
}