var AddNewTodoModal = {

    init: function(modal) {
        this._modal = modal;
        AddNewTodoModal._getSaveButton().onclick = AddNewTodoModal._save
    },

    _getDataToSave: function() {
        return {
            description: this._getDescriptionElement().value,
            date: this._getDateElement().value
        }
    },

    _save: function() {

        console.log("Saving data", JSON.stringify(AddNewTodoModal._getDataToSave()))

         let request = new Request (
            "http://localhost:8080/new",
               {
                   method: "POST",
                   body: JSON.stringify(AddNewTodoModal._getDataToSave())
               });
           fetch(request).then(function(response) {
               location.reload();
               });
    },

    _getSaveButton: function() {
        return this._modal.getElementsByClassName('saveInAddNewModal')[0];
    },

    _getDescriptionElement: function() {
        return this._modal.getElementsByClassName('descriptionInAddNewModal')[0];
    },

    _getDateElement: function(){
        return this._modal.getElementsByClassName('dueDateInAddNewModal')[0];
    }

}