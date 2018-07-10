function changeStatus() {
       var status = document.getElementById("selectStatus").value;
       if (status === "ALL TODOS") {
           location.href = "todos"
       }
       else {
           location.href = "todos?status=" + status;
       }
 }

 function searchTodo() {
        var description = document.getElementById("description").value;
        var status = document.getElementById("selectStatus").value;
        location.href = "todos?status=" + status + "&search=" + description;


 }


