                    //see on javascripti klass, ainult et ei saa teha mitut selle klassi objekti
                    //see on nagu suur jason objekt, sellep'rast  : ja komad meetodite vahel
var TodoModal = {
    foo: 'tere',    //see on lihtsalt muutuja, kui teda väljaspool seda klassi välja kutsuda TodoModal.foo(), siis ei juhtu midagi, sest ta ei ole funktsioon ega tagasta midagi.
                    //_modal võiks olla siin ka defineeritud klassi muutujana (tühjana), aga seda pole vaja eraldi defineerida nagu nt javas, saab funktsiooni sees ka tekitada

                    //selle funktsiooniga tehakse klassimuutuja _modal ja öeldakse, et see, mis parameetriks sisse tuleb, ongi see
    init: function(modal) {
        this._modal = modal;
        console.log('Initialized TodoModal', modal)

        // kui vajutatakse save nupu peale, siis salvesta..
        // _getSaveButton().onclick = _save
    },
                        //täidab elemendid infoga
    show: function(data) {
        this._getDescriptionElement().value = data.description;
        this._getStatusElement().innerHTML = data.status;
        this._getDateElement().value = data.dueDate;
    },

    hide: function() {
        // OPTIONAL: kui leiad koha, või mingi meetodi, kuidas kuulata kui modaal kinni pannakse.. bootstrapis peaks midagi olema..
        this._getDescriptionElement().value = '';
    },

    _getData: function() {
        console.log('My modal is: ', this._modal)
        return {
            description: this._getDescriptionElement().value,
            status: this._getStatusElement().value,
            date: this._getDateElement().value
        }
    },

    _save: function() {
        // Tee POST ../todos/update päring, data saad _getData() funktsioonist


           var bodyJson = {
                   description: _getData().description,
                   status: _getData().status,
                   date: _getData().date
               };
           let request = new Request (
               "http://localhost:8080/todos/update",
               {
                   method: "POST",
                   body: JSON.stringify(bodyJson)
               });

               //kuhu need kaks rida lähevad?
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