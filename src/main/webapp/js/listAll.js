window.addEventListener("load", function(){
    AddNewTodoModal.init(document.getElementById("addNewModal"));

    var todoCardList = new TodoCardList(document.getElementsByClassName("cardContainer")[0]);
    todoCardList.init()

    var textInputSearch = document.getElementById("descriptionSearched");
    var timeout = null;
        textInputSearch.onkeyup = function (e) {
        clearTimeout(timeout);
        timeout = setTimeout(function () {searchTodos()}, 500);
        };
    });

function searchTodos() {
    location.href = "?" + generateStringForUrl();
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

//function checkIfTitleIsNotEmptyAndSubmit() {
//    var titleOfNewTodo = document.getElementById("titleInAddNewModal").value;
//    if (titleOfNewTodo === null || titleOfNewTodo ==="") {
//        var errorMessage = document.getElementById("errorMessage");
//        errorMessage.hidden = false;
//    } else {
//        submitNewTodo()
//    }
//}
//
//function submitNewTodo() {
//    document.getElementById("submitNewTodo").submit();
//}
