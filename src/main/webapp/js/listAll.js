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
});

function searchTodos() {
    location.href = "todos?" + generateStringForUrl();
}

function addDueDate(event) {
    var dateInputButton = event.target;
//      console.log(dateInputButton);
    var todoRow = dateInputButton.closest(".todoRow");
//      console.log(todoRow);
    var idOfTodoToChangeDate = todoRow.dataset.id;
//      console.log(idOfTodoToChangeDate);
    var date = event.target.value;
//      console.log(date);

    let request = new Request("http://localhost:8080/todos?date=" + date + "&id=" + idOfTodoToChangeDate, {method: "PUT"})
    fetch(request).then(function(response) {
        location.reload();
    });

}

//function changeStatusToDone(id) {
//    let request = new Request("http://localhost:8080/todos/done=" + id, {method: "PUT"});
//    fetch(request).then(function(response) {
//        location.reload();
//    });
//}

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



function deleteTodo(id) {
    let request = new Request("http://localhost:8080/todos?delete=" + id, {method: "DELETE"});
    fetch(request).then(function(response) {
        location.reload();
    });
}