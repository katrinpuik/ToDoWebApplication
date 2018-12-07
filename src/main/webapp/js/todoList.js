function TodoList(element) {
    const self = this;
    var _element = element;
    var _todoRows = [];

    self.init = function() {
        console.log("Initializing TodoList. Creating rows");
        Array.from(_element.getElementsByClassName("todoRow")).forEach(function (rowElement) {
            var row = new TodoListRow(rowElement);
            row.init();
            _todoRows.push(row);
        });

        setInterval(self._updateRows, 5000);
    }

    self._updateRows = function() {
        _todoRows.forEach(function(row) {
            row.update();
        })
    }
}