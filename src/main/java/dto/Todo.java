package dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import enums.Status;

import java.util.Date;
import java.util.List;

import static enums.Status.valueOf;

public class Todo {
    private Integer id;
    private String title;
    private String description;
    private List <String> labels;
    private Status status;
    private Date creationDate;
    private Date updateDate;
    private Date doneDate;
    private Date dueDate;

    public Todo(String title, Status status) {
        this.title = title;
        this.status = status;
    }

    public Todo (String title, Status status, Integer id, Date dueDate) {
        this(title, status);
        this.id = id;
        this.dueDate = dueDate;
    }

    public Todo (String title, Status status, Integer id, Date dueDate, String description, Date doneDate) {
        this(title, status);
        this.id = id;
        this.dueDate = dueDate;
        this.description = description;
        this.doneDate = doneDate;
    }

    public Todo (String title, Status status, Date dueDate) {
        this(title, status);
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String>  getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getDueDate() {
        return dueDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getCreationDate() {
        return creationDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getUpdateDate() {
        return updateDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getDoneDate() {
        return doneDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return description + ", " + String.valueOf(status) + ", id" + id;
    }
}