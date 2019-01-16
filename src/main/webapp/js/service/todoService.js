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

    //see kirjutada showModal j'rgi, aga viimane ots j'tta todoListRow teha
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

//    showModal: function(id) {
//        let request = new Request (
//            TodoService._getUrl("/todos/todo?id=" + id),
//            {
//                method: "GET"
//            });
//        return fetch(request)
//            .then(function(response) {
//                  return response.json();
//             })
//             .then(function(jsonResponse) {
//
//             //seda teeb todoListRow
//                  TodoModal.show(jsonResponse)
//             });
//    },




    _getUrl: function(path) {
        return "http://localhost:8080" + path;
    }
}


