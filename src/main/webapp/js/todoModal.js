var TodoModal = {
    foo: 'tere',

    init: function(modal) {
        this._modal = modal;
        console.log('Initialized TodoModal', modal)

        // kui vajutatakse save nupu peale, siis salvesta..
        // _getSaveButton().onclick = _save
    },

    show: function(data) {
        this._getDescriptionElement().value = data.description;
    },

    hide: function() {
        // OPTIONAL: kui leiad koha, või mingi meetodi, kuidas kuulata kui modaal kinni pannakse.. bootstrapis peaks midagi olema..
        this._getDescriptionElement().value = '';
    },

    _getData: function() {
        console.log('My modal is: ', this._modal)
        return {
            description: this._getDescriptionElement().value,
            status: ''
        }
    },

    _save: function() {
        // Tee POST ../todos/update päring, data saad _getData() funktsioonist
    },

    _getSaveButton: function() {
        return null; // TODO: tagasta save nupp
    },

    _getDescriptionElement: function() {
        return this._modal.getElementsByClassName('descriptionInModal')[0];
    }
}