var TodoService = {

    updateStatusToDone: function(id) {
        let request = new Request(
            TodoService._getUrl("/done?id=" + id),
            {
                method: "PUT",
            });
        return fetch(request);
    },

    deleteTodo: function(id) {
        let request = new Request (
            TodoService._getUrl("/delete?id=" + id),
            {
                method: "DELETE"
            });
        return fetch(request);
    },

    getTodo: function(id) {
        let request = new Request (
            TodoService._getUrl("/todo?id=" + id),
            {
                method: "GET"
            });
                return fetch(request)
                    .then(function(response) {
                          return response.json();
                     });
    },

    saveDataFromExpandedView: function(data) {
        let request = new Request (
        TodoService._getUrl("/update?id=" + data.id),
        {
            method: "POST",
            body: JSON.stringify(data)
        });
        return fetch(request);
    },




    _getUrl: function(path) {
        return "http://localhost:8080" + path;
    }
}


