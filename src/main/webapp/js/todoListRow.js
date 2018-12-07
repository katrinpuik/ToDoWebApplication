function TodoListRow(element) {
    const self = this;
    var _element = element;

    self.init = function() {
        console.log("Initializing TodoListRow", _element);

        var doneButton = self._getToDoneButtonElement();
        if (doneButton) {
            doneButton.onclick = self._changeStatusToDone
        }

        // TODO: Add all other buttons here as well
//       Array.from(document.getElementsByClassName("toDelete")).forEach(function(todoToDelete) {
//           todoToDelete.onclick = deleteTodo;
//       });
//       Array.from(document.getElementsByClassName("edit")).forEach(function(todoToEdit) {
//           todoToEdit.onclick = showModal;
//       });
    }

    self.update = function() {
        // TODO: update time left
        console.log("I should update time left");
    }

    self._getId = function() {
        return _element.dataset.id;
    }

    self._changeStatusToDone = function(event) {
        TodoService.updateStatusToDone(self._getId()).then(function(response) {
            location.reload();
        });
    }

    self._getToDoneButtonElement = function() {
        return _element.getElementsByClassName('toDone')[0];
    }
}