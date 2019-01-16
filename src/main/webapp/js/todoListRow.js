function TodoListRow(element) {
    const self = this;
    var _element = element;

    self.init = function() {
        //console.log("Initializing TodoListRow", _element);

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

    self.update = function() {
            // TODO: update time left
     //       console.log("I should update time left");
    }

    self._getId = function() {
            return _element.dataset.id;
    }
}


// Array.from(document.getElementsByClassName("timeLeft")).forEach(function (timeLeftBox) {
//        var row = timeLeftBox.closest(".todoRow");
//        var dateInput = row.getElementsByClassName("dueDate")[0];
//        if (dateInput) {
//            var difference = calculateTimeLeft(dateInput.innerHTML);
//             console.log("test", difference, dateInput.innerHTML);
//            if (difference > 0) {
//
//                timeLeftBox.innerHTML = difference + " days left";
//
//            } else if (difference < 0) {
//                timeLeftBox.innerHTML = Math.abs(difference) + " days due";
//            }
//        }
//   });
