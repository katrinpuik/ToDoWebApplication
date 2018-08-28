function searchTodos() {
    var description = document.getElementById("description").value;
    var status = document.getElementById("selectStatus").value;

    if (status !== null && description !== null) {
        location.href = "todos?status=" + status + "&description=" + description;
    } else if (status !== null) {
        location.href = "todos?status=" + status;
    } else if (description !== null) {
        location.href = "todos?description" + description;
    } else {
        location.href = "todos";
    }
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
    let request = new Request("http://localhost:8080/todos?done=" + id, {method: "PUT"} );
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