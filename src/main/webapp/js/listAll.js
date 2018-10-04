window.addEventListener("load", function(){
   var textInput = document.getElementById("description");
   var timeout = null;
   textInput.onkeyup = function (e) {
       clearTimeout(timeout);
       timeout = setTimeout(function () {searchTodos()}, 500);
   };
   var dateInputs = Array.from(document.getElementsByClassName("dueDate")).forEach(function(dateInput) {
       dateInput.onchange = addDueDate;
   });
   var todosToChangeToDone = Array.from(document.getElementsByClassName("btn btn-primary toDone")).forEach(function(todoToDone) {
       todoToDone.onclick = changeStatusToDone;
   });
   var todosToDelete = Array.from(document.getElementsByClassName("btn btn-primary toDelete")).forEach(function(todoToDelete) {
       todoToDelete.onclick = deleteTodo;
   })

});

function searchTodos() {
    location.href = "todos?" + generateStringForUrl();
}

function addDueDate(event) {
    var dateInputButton = event.target;
    var todoRow = dateInputButton.closest(".todoRow");
    var idOfTodoToChangeDate = todoRow.dataset.id;
    var date = event.target.value;

    let request = new Request("http://localhost:8080/todos?date=" + date + "&id=" + idOfTodoToChangeDate, {method: "PUT"})
    fetch(request).then(function(response) {
        location.reload();
    });
}

function changeStatusToDone(event) {
    var todoToDoneButton = event.target;
    var todoRow = todoToDoneButton.closest(".todoRow");
    var idOfTodoToChangeToDone = todoRow.dataset.id;

    let request = new Request("http://localhost:8080/todos/done?id=" + idOfTodoToChangeToDone, {method: "PUT"});
    fetch(request).then(function(response) {
        location.reload();
    });
}

function deleteTodo(event) {
    var todoToDeleteButton = event.target;
    var todoRow = todoToDeleteButton.closest(".todoRow");
    var idOfTodoToDelete = todoRow.dataset.id;

    let request = new Request("http://localhost:8080/todos/delete?id=" + idOfTodoToDelete, {method: "DELETE"});
    fetch(request).then(function(response) {
        location.reload();
    });
}

function getDataForGeneratingUrl() {
     var parameters = new Map();
     parameters.set("status", document.getElementById("selectStatus").value);
     var description = document.getElementById("description").value;
        if (description !== null && description !== undefined && description !=="") {
        parameters.set("description", description);
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

