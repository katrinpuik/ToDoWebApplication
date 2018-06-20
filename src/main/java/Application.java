import dto.ToDo;
import enums.Status;
import exception.ServiceException;
import service.ToDoMapper;
import service.ToDoService;
import service.ToDoValidator;

import java.io.IOException;
import java.util.List;

import static helper.ReadingAndWritingToFileHelper.initStringsFromFile;
import static helper.ReadingAndWritingToFileHelper.writeRowsToFile;

public class Application {

    private static ToDoService service = new ToDoService();
    private static ToDoMapper mapper = new ToDoMapper();
    private static ToDoValidator validator = new ToDoValidator();


    public static void main(String[] args) throws IOException {
        System.out.println("ToDos from file: ");
        mapper.deserialize(initStringsFromFile())
                .forEach(toDoFromFile -> {
                    try {
                        service.save(toDoFromFile);
                        System.out.println(toDoFromFile);
                    } catch (ServiceException e) {
                        System.out.println(e.getMessage());
                    }
                });
        System.out.println("------------");

        runProgram();

        System.out.println("-------------");
        service.getAll().forEach((x) -> System.out.println(x));
        writeRowsToFile(mapper.serialize(service.getAll()));
    }

    private static void runProgram() {
        createToDo("very important thing to do", "NOT_DONE");
        createToDo("not so important thing to do", "DISCARDED");
        createToDo("something", "DONE");
        System.out.println("3 toDos added");
        service.getAll().forEach(System.out::println);
        System.out.println("----------------");

        ToDo toDoFoundByDescription = service.findByDescription("say hello").get(0);
        System.out.println("ToDo found by description: " + toDoFoundByDescription);
        System.out.println("------------");

        List<ToDo> toDosFoundByStatus = service.findByStatus(Status.DONE);
        System.out.println("ToDos found by status DONE: ");
        toDosFoundByStatus.forEach(System.out::println);
        System.out.println("-----------------");

        Integer idOfTheToDoWithTheDescriptionSayHello = service.findByDescription("say hello").get(0).getId();
        ToDo toDoFoundById = service.findById(idOfTheToDoWithTheDescriptionSayHello);
        System.out.println("ToDo found by id: " + toDoFoundById);
        System.out.println("-------------");

        service.remove("SAY hello");
        List<ToDo> toDosFoundByDescription = service.findByDescription("say GOODbye");
        service.remove(toDosFoundByDescription.get(0).getId());
        System.out.println("ToDo found by description 'say hello' and toDo found by id 'say goodbye' removed");
        service.getAll().forEach(System.out::println);
        System.out.println("--------------");

        ToDo toDoWithNewDescriptionAndNewStatus = service.findByDescription("something").get(0);
        String newDescription = "new description";
        try {
            if (validator.isValidDescription(newDescription)) {
                toDoWithNewDescriptionAndNewStatus.setDescription(newDescription);
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        String newStatus = "NOT_DONE";
        try {
            if (validator.isValidStatus(newStatus)) {
                toDoWithNewDescriptionAndNewStatus.setStatus(newStatus);
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        try {
            service.save(toDoWithNewDescriptionAndNewStatus);
        } catch (ServiceException e1) {
            System.out.println(e1.getMessage());
        }

        System.out.println("toDo with description 'something', status 'DONE' and id 10 is now " + toDoWithNewDescriptionAndNewStatus);
        System.out.println("----------------");

        System.out.println("Trying to create toDo with invalid description and invalid status: ");
        String emptyInvalidDescription = "";
        try {
            if (validator.isValidDescription(emptyInvalidDescription)) {
                toDoWithNewDescriptionAndNewStatus.setDescription(emptyInvalidDescription);
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        String newInvalidStatus = "lkasdjf";
        try {
            if (validator.isValidStatus(newInvalidStatus)) {
                toDoWithNewDescriptionAndNewStatus.setStatus(newInvalidStatus);
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-------------");
        System.out.println("Trying to update toDo which is not in database");
        ToDo toDoWithNoSuchID = new ToDo("toDoToTest");
        toDoWithNoSuchID.setStatus("DONE");
        toDoWithNoSuchID.setId(9087);
        try {
            service.save(toDoWithNoSuchID);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createToDo(String description, String status) {
        try {
            if (validator.isValidDescription(description) && validator.isValidStatus(status.toUpperCase())) {
                ToDo toDo = new ToDo(description);
                toDo.setStatus(status);
                service.save(toDo);
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }
}
