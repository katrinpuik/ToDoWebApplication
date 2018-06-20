package service;

import exception.ServiceException;

import static enums.Status.valueOf;

public class ToDoValidator {

    public boolean isValidDescription(String description) throws ServiceException {
        if (description != null && !description.isEmpty()) {
            return true;
        }
        throw new ServiceException("Invalid description");
    }

    public boolean isValidStatus(String status) throws ServiceException {
        if (status != null) {
            try {
                valueOf(status);
            } catch (IllegalArgumentException e) {
                throw new ServiceException("Invalid status");
            }
        }
        return true;
    }
}
