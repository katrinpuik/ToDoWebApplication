var TodoService = {
    updateStatusToDone: function(id) {
        let request = new Request(
            TodoService._getUrl("/todos/done?id=" + id),
            {
                method: "PUT",
            });
        return fetch(request);
    },

    _getUrl: function(path) {
        return "http://localhost:8080" + path;
    }
}
