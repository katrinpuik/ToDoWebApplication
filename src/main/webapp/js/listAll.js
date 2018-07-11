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
