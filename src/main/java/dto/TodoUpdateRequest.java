package dto;

public class TodoUpdateRequest {
    private Integer id;
    private String title;
    private String description;
    private String dueDate;


    public Integer getId() {
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }
}


