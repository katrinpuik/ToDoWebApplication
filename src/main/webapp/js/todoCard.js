function TodoCard(element) {
    const self = this;
    var _element = element;
    var _statusColorElement = _element.getElementsByClassName("statusColor")[0];

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

        if (_element.dataset.status==='DONE') {
           _statusColorElement.classList.add('doneGreen');
        } else {
            self._addDateAndStatusColor();
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

    self._getId = function() {
        return _element.dataset.id;
    }

    self._getDateField = function() {
        return _element.getElementsByClassName('dateField')[0];
    }

    self.update = function() {
        if(_element.dataset.status != "DONE") {
            self._addDateAndStatusColor();
        }
    }

    self._addDateAndStatusColor = function() {
        var dueDate = _element.dataset.duedate;
        if(dueDate) {
            var difference = self._calculateTimeLeft(dueDate);
            if (difference > 0) {
                self._getDateField().innerHTML = difference + " days left";
                _statusColorElement.classList.add('notDoneYellow');
            } else if (difference < 0) {
                self._getDateField().innerHTML = Math.abs(difference) + " days due";
                _statusColorElement.classList.add('notDoneRed');
            }
        }
    }


    self. _calculateTimeLeft = function (dateString) {
        if(dateString != "") {
            var date = new Date(dateString);
            var dateNow = new Date();
                return Math.floor((date-dateNow) / 1000 / 60 / 60 / 24);
        }
    }
}

