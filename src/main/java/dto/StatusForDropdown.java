package dto;

import enums.Status;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusForDropdown that = (StatusForDropdown) o;
        return isSelected == that.isSelected &&
                value == that.value &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, name, isSelected);
    }
}

