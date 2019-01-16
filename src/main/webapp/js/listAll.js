window.addEventListener("load", function(){
    TodoModal.init(document.getElementById("editModal"));

    var todoList = new TodoList(document.getElementsByClassName("todoListContainer")[0]);
    todoList.init()

   var textInputSearch = document.getElementById("descriptionSearched");
   var timeout = null;
   textInputSearch.onkeyup = function (e) {
       clearTimeout(timeout);
       timeout = setTimeout(function () {searchTodos()}, 500);
   };

   // TODO: move to todoListRow
   Array.from(document.getElementsByClassName("timeLeft")).forEach(function (timeLeftBox) {
        var row = timeLeftBox.closest(".todoRow");
        var dateInput = row.getElementsByClassName("dueDate")[0];
        if (dateInput) {
            var difference = calculateTimeLeft(dateInput.innerHTML);
            if (difference > 0) {

                timeLeftBox.innerHTML = difference + " days left";

            } else if (difference < 0) {
                timeLeftBox.innerHTML = Math.abs(difference) + " days due";
            }
        }
   });

    // TODO: move to todoListRow
//   Array.from(document.getElementsByClassName("toDelete")).forEach(function(todoToDelete) {
//       todoToDelete.onclick = deleteTodo;
//   });
//   Array.from(document.getElementsByClassName("edit")).forEach(function(todoToEdit) {
//       todoToEdit.onclick = showModal;
//   });
});

// TODO: move to todoListRow
//function showModal(event) {
//    var editButton = event.target;
//    var idOfTodoToEdit = getClosestRowId(editButton);
//
//    // TODO: move to TodoService.getTodo(id)
//   let request = new Request("http://localhost:8080/todos/todo?id=" + idOfTodoToEdit, {method: "GET"});
//
//   fetch(request)
//        .then(function(response) {
//            return response.json();
//        })
//        .then(function(jsonResponse) {
//            TodoModal.show(jsonResponse)
//        });
//}

function calculateTimeLeft(dateString) {
    if(dateString != "") {
        var date = new Date(dateString);
        var dateNow = new Date();
            return Math.floor((date-dateNow) / 1000 / 60 / 60/ 24);
    }
}

//// TODO: move to todoListRow
//function deleteTodo(event) {
//    var todoToDeleteButton = event.target;
//    var idOfTodoToDelete = getClosestRowId(todoToDeleteButton);
//
//    // TODO: move to TodoService.delete(id)
//    let request = new Request("http://localhost:8080/todos/delete?id=" + idOfTodoToDelete, {method: "DELETE"});
//    fetch(request).then(function(response) {
//        location.reload();
//    });
//}

// TODO: remove later
function getClosestRowId(element) {
    return element.closest(".todoRow").dataset.id;
}

function searchTodos() {
    location.href = "todos?" + generateStringForUrl();
}

function getDataForGeneratingUrl() {
     var parameters = new Map();
     parameters.set("status", document.getElementById("selectStatus").value);
     var description = document.getElementById("descriptionSearched").value;
        if (description !== null && description !== undefined && description !=="") {
        parameters.set("descriptionSearched", description);
        }
     return parameters;
}

function generateStringForUrl() {
    var arrayFromDataToUrl = [];
    getDataForGeneratingUrl().forEach(function(value, key) {
        arrayFromDataToUrl.push(key + "=" + value);
    });
    return arrayFromDataToUrl.join("&");
}

function checkIfDescriptionIsNotEmptyAndSubmit() {
    var descriptionOfNewTodo = document.getElementById("newTodo").value;
    if (descriptionOfNewTodo === null || descriptionOfNewTodo ==="") {
        var errorMessage = document.getElementById("errorMessage");
        errorMessage.hidden = false;
    } else {
        submitNewTodo()
    }
}

function submitNewTodo() {
    document.getElementById("submitNewTodo").submit();
}


