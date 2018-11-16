window.addEventListener("load", function(){
   var textInputSearch = document.getElementById("descriptionSearched");
   var timeout = null;
   textInputSearch.onkeyup = function (e) {
       clearTimeout(timeout);
       timeout = setTimeout(function () {searchTodos()}, 500);
   };

   Array.from(document.getElementsByClassName("description")).forEach(function(descriptionInput) {
        var timeout = null;
        descriptionInput.onkeyup = function (event) {
            clearTimeout(timeout);
            timeout = setTimeout(function () {updateDescription(event)}, 500);
        };
   });

   Array.from(document.getElementsByClassName("dueDate")).forEach(function(dateInput) {
       dateInput.onchange = addDueDate;

       var row = dateInput.closest(".todoRow");
       var timeLeftBox = row.getElementsByClassName("timeLeft")[0];
       if (timeLeftBox) {
           var difference = calculateTimeLeft(dateInput);
           if (difference > 0) {
                timeLeftBox.innerHTML = difference + " days left";
           } else if (difference < 0) {
                timeLeftBox.innerHTML = Math.abs(difference) + " days due";
           }
       }
   });

   Array.from(document.getElementsByClassName("toDone")).forEach(function(todoToDone) {
       todoToDone.onclick = changeStatusToDone;
   });
   Array.from(document.getElementsByClassName("toDelete")).forEach(function(todoToDelete) {
       todoToDelete.onclick = deleteTodo;
   });
   Array.from(document.getElementsByClassName("edit")).forEach(function(todoToEdit) {
       todoToEdit.onclick = showModal;
   });
});


function changeStatusToDone(event) {
    var todoToDoneButton = event.target;
    var idOfTodoToChangeToDone = getClosestRowId(todoToDoneButton);

    let request = new Request(
        "http://localhost:8080/todos/done?id=" + idOfTodoToChangeToDone,
        {
            method: "PUT",
        });
    fetch(request).then(function(response) {
        location.reload();
    });
}

function addDueDate(event) {
    var dueDateBox = event.target;
    var bodyJson = {
            id:getClosestRowId(dueDateBox),
            date:dueDateBox.value
            };

    let request = new Request(
        "http://localhost:8080/todos/update",
        {
            method: "POST",
            body: JSON.stringify(bodyJson)
        });
    fetch(request).then(function(response) {
        location.reload();
    });
}

function updateDescription(event) {
    var descriptionBox = event.target;
    var bodyJson = {
        id:getClosestRowId(descriptionBox),
        description:descriptionBox.value
        };
    let request = new Request (
        "http://localhost:8080/todos/update",
        {
            method: "POST",
            body: JSON.stringify(bodyJson)
        });
    fetch(request).then(function(response) {
        location.reload();
        });
}

function showModal(event) {
    var editButton = event.target;
    var idOfTodoToEdit = getClosestRowId(editButton);

   let request = new Request("http://localhost:8080/todos/todo?id=" + idOfTodoToEdit, {method: "GET"});

   fetch(request)
        .then(function(response) {
            return response.json();
        })
        .then(function(jsonResponse) {
           fillModalWithData(jsonResponse)
        });

    function fillModalWithData(data) {
        var modal = document.getElementById("editModal");
        modal.getElementsByClassName('descriptionInModal')[0].value= data.description;
        modal.getElementsByClassName('statusInModal')[0].innerHTML=data.status;
        modal.getElementsByClassName('dueDateInModal')[0].value=data.dueDate;
    }
}

function calculateTimeLeft(dateInput) {
    if(dateInput.value != "") {
        var date = new Date(dateInput.value);
        var dateNow = new Date();
            return Math.floor((date-dateNow) / 1000 / 60 / 60/ 24);
    }
}

function deleteTodo(event) {
    var todoToDeleteButton = event.target;
    var idOfTodoToDelete = getClosestRowId(todoToDeleteButton);

    let request = new Request("http://localhost:8080/todos/delete?id=" + idOfTodoToDelete, {method: "DELETE"});
    fetch(request).then(function(response) {
        location.reload();
    });
}

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


// näide sellest, kuidas teades üht rea elementi (selle saab ikka event.target'iga), saab kätte kõik
// teised selle rea elemendid

//function getClosestRow(element) {
//    var row = element.closest(".todoRow");
//    if (row) {
//        return {
//            id: row.dataset.id,
//            doneButton: row.getElementsByClassName("toDone")[0],
//            deleteButton: row.getElementsByClassName("toDelete")[0],
//            dueDateBox: row.getElementsByClassName("dueDate")[0],
//            timeLeftBox: row.getElementsByClassName("timeLeft")[0]
//        }
//    }
//    return {}
//}

// andmeid saab nii
// var data = getClosestRow(element);
// var id = data.id;
// var deleteButton = data.deleteButton;