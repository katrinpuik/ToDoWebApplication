function TodoCardList(element) {
    const self = this;
    var _element = element;
    var _todoCards = [];

    self.init = function() {
        Array.from(_element.getElementsByClassName("todoCard")).forEach(function (cardElement) {
            var card = new TodoCard(cardElement);
            card.init();
            _todoCards.push(card);
        });

        setInterval(self._updateCards, 5000);
    }

    self._updateCards = function() {
        _todoCards.forEach(function(card) {
            card.update();
        })
    }
}