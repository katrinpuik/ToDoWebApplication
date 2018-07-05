function changeStatus() {
       var status = document.getElementById("selectStatus").value;
       if (status === "ALL TODOS") {
           location.href = "todos"
       }
       else {
           location.href = "todos?status=" + status;
       }
 }


