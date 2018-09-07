package dto;

import enums.Status;

public class StatusForDropdown {
    private Status value;
    private String name;
    private boolean isSelected;

    public StatusForDropdown(Status value, String name, boolean isSelected) {
        this.value = value;
        this.name = name;
        this.isSelected = isSelected;
    }

    public Status getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return isSelected;
    }
}

