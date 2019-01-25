var TodoService = {

    updateStatusToDone: function(id) {
        let request = new Request(
            TodoService._getUrl("/todos/done?id=" + id),
            {
                method: "PUT",
            });
        return fetch(request);
    },

    deleteTodo: function(id) {
        let request = new Request (
            TodoService._getUrl("/todos/delete?id=" + id),
            {
                method: "DELETE"
            });
        return fetch(request);
    },

    getTodo: function(id) {
        let request = new Request (
            TodoService._getUrl("/todos/todo?id=" + id),
            {
                method: "GET"
            });
                return fetch(request)
                    .then(function(response) {
                          return response.json();
                     });
    },

    _getUrl: function(path) {
        return "http://localhost:8080" + path;
    }
}


