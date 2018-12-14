function TodoListRow(element) {
    const self = this;
    var _element = element;

    self.init = function() {
        console.log("Initializing TodoListRow", _element);

        var doneButton = self._getDoneButtonElement();
        if (doneButton) {
            doneButton.onclick = self._changeStatusToDone
        }

        var deleteButton = self._getDeleteButtonElement();
        if (deleteButton) {
            deleteButton.onclick = self._delete
        }

        var modalButton = self._getModalButtonElement();
        if (modalButton) {
            modalButton.onclick = self._showModal
        }



//       Array.from(document.getElementsByClassName("edit")).forEach(function(todoToEdit) {
//           todoToEdit.onclick = showModal;
//       });

    }

    self._changeStatusToDone = function() {
        TodoService.updateStatusToDone(self._getId()).then(function(response) {
            location.reload();
        });
    }

    self._delete = function() {
        TodoService.deleteTodo(self._getId()).then(function(response) {
            location.reload();
        });
    }

    self._showModal = function(){
        TodoService.showModal(self._getId).then(function(response) {
            location.reload();
        });
    }

    self._getDoneButtonElement = function() {
        return _element.getElementsByClassName('toDone')[0];
    }

    self._getDeleteButtonElement = function(){
        return _element.getElementsByClassName('toDelete')[0];
    }

    self._getModalButtonElement() = function() {
        return _element.getElementsByClassName('edit')[0];

    }

    self.update = function() {
            // TODO: update time left
            console.log("I should update time left");
    }

    self._getId = function() {
            return _element.dataset.id;
    }
}