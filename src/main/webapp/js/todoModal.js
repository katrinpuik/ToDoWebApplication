var TodoModal = {
    init: function(modal) {
        this._modal = modal;
      //  console.log('Initialized TodoModal', modal)
        TodoModal._getSaveButton().onclick = TodoModal._save
    },
                        //täidab elemendid andmebaasist võetava infoga (kui modalit kasutajale kuvatakse)
    show: function(data) {
        this._currentId = data.id;
        this._getDescriptionElement().value = data.description;
        this._getStatusElement().innerHTML = data.status;
        this._getDateElement().value = data.dueDate;
    },
                        //puhastab modali, hetkel pole see funktsioon küll kasutuses
    hide: function() {
        this._currentId = undefined;
        this._getDescriptionElement().value = '';
        this._getStatusElement().innerHTML = '';
        this._getDateElement.value = '';
    },
                        //annab info, mis modalis parasjagu on
    _getData: function() {
       // console.log('My modal is: ', this._modal)
        return {
            id: this._currentId,
            description: this._getDescriptionElement().value,
            status: this._getStatusElement().value,
            date: this._getDateElement().value
        }
    },

    _save: function() {
//        console.log("This in _save", this)
//        console.log("TodoModal in _save", TodoModal)
//        console.log("Saving data", JSON.stringify(TodoModal._getData()))
           let request = new Request (
               "http://localhost:8080/update",
               {
                   method: "POST",
                   body: JSON.stringify(TodoModal._getData())
               });

           fetch(request).then(function(response) {
               location.reload();
               });
    },

    _getSaveButton: function() {
        return this._modal.getElementsByClassName('saveInModal')[0];
    },


                        //küsib välja tühjad elemendid
    _getDescriptionElement: function() {
        return this._modal.getElementsByClassName('descriptionInModal')[0];
    },

    _getStatusElement: function(){
        return this._modal.getElementsByClassName('statusInModal')[0];
    },

    _getDateElement: function(){
        return this._modal.getElementsByClassName('dueDateInModal')[0];
    }
}