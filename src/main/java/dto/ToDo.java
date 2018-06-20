package dto;


import enums.Status;

import static enums.Status.valueOf;

public class ToDo {
    private Integer id;
    private String description;
    private Status status;

    public ToDo(String description) {
        this.description = description;
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
        if (status == null) {
            this.status = null;
        } else {
            this.status = valueOf(status);
        }
    }

    public Integer getId(){
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return description + ", " + String.valueOf(status) + ", id" + id;
    }
}
