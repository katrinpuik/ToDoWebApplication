function TodoListRow(element) {
    const self = this;
    var _element = element;

    self.init = function() {

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
        TodoService.getTodo(self._getId()).then(function(todo){
        TodoModal.show(todo);
        });
    }

    self._getDoneButtonElement = function() {
        return _element.getElementsByClassName('toDone')[0];
    }

    self._getDeleteButtonElement = function() {
        return _element.getElementsByClassName('toDelete')[0];
    }

    self._getModalButtonElement = function() {
        return _element.getElementsByClassName('edit')[0];
    }

    self._getTimeLeftElement = function() {
        return _element.getElementsByClassName('timeLeft')[0];
    }

    self._getDueDateElement = function(){
        return _element.getElementsByClassName('dueDate')[0];
    }

    self.update = function() {
        var dueDateAsString = self._getDueDateElement().innerHTML;
        if(dueDateAsString) {
            var difference = self._calculateTimeLeft(dueDateAsString);
            if (difference > 0) {
                self._getTimeLeftElement().innerHTML = difference + " days left";
            } else if (difference < 0) {
                self._getTimeLeftElement().innerHTML = Math.abs(difference) + " days due";
            }
        }
    }

    self._getId = function() {
            return _element.dataset.id;
    }

    self. _calculateTimeLeft = function (dateString) {
        if(dateString != "") {
            var date = new Date(dateString);
            var dateNow = new Date();
                return Math.floor((date-dateNow) / 1000 / 60 / 60/ 24);
        }
    }

}