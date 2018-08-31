function searchTodos() {
    location.href = "todos?" + generateStringForUrl();
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
    var dataToUrl = getDataForGeneratingUrl();
    var arrayFromDataToUrl = [];
        for (var [key, value] of dataToUrl){
            var itemToArray = key + "=" + value;
            arrayFromDataToUrl.push(itemToArray);
        }
        var stringToUrl = arrayFromDataToUrl.join("&");
     return stringToUrl;


//    var mapRowsCounter = 1;
//    for (var [key, value] of dataToUrl) {
//            if (mapRowsCounter< dataToUrl.size) {
//                stringToUrl += key + "=" + value + "&";
//                mapRowsCounter++;
//            } else {
//                stringToUrl += key + "=" + value;
//            }
//        }
//    return stringToUrl;
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

function changeStatusToDone(id) {
    let request = new Request("http://localhost:8080/todos?done=" + id, {method: "PUT"});
    fetch(request).then(function(response) {
        location.reload();
    });
}

function deleteTodo(id) {
    let request = new Request("http://localhost:8080/todos?delete=" + id, {method: "DELETE"});
    fetch(request).then(function(response) {
        location.reload();
    });
}