package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import enums.Status;

import java.util.Date;
import java.util.List;

public class TodoRenderObject {
    private Integer id;
    private String title;
    private String description;
    private List<String> labels;
    private Status status;
    private Date creationDate;
    private Date updateDate;
    private Date doneDate;
    private Date dueDate;
    private String expandedUrl;
    private String simpleUrl;
    private boolean expanded;

    public TodoRenderObject(
            Integer id,
            String title,
            String description,
            List<String> labels,
            Status status,
            Date creationDate,
            Date doneDate,
            Date updateDate,
            Date dueDate,
            String expandedUrl,
            String simpleUrl,
            boolean expanded) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.labels = labels;
        this.status = status;
        this.creationDate = creationDate;
        this.doneDate = doneDate;
        this.updateDate = updateDate;
        this.dueDate = dueDate;
        this.expandedUrl = expandedUrl;
        this.simpleUrl = simpleUrl;
        this.expanded = expanded;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean isExpanded() {
        return expanded;
    }

    public String getExpandedUrl() {
        return expandedUrl;
    }

    public String getSimpleUrl(){
        return simpleUrl;
    }


    public boolean isCompletable() {
        return status == Status.NOT_DONE;
    }

    public boolean isDone() {
        return status == Status.DONE;
    }


}


