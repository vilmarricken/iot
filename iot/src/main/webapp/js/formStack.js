function FormStack ( _state, _model ) {
	this.state = _state;
	this.model = _model;
	this.build();
}

Form.prototype.getElement = function() {
	return this.element;
}

Form.prototype.build = function() {
	form = document.createElement("div");
	form.innerHTML = this.state;
	form.appendChild(createButton("close"));
	this.element = form;	
};

function createButton( name ) {
	element = document.createElement("input");
	element.setAttribute("type", "button");
	element.setAttribute("id", name);
	element.setAttribute("name", name);
	element.setAttribute("value", name);
	element.setAttribute("onClick", "application.closeForm()");
	return element;
}
