function FormStack ( _state, _model ) {
	this.state = _state;
	this.model = _model;
	this.build();
}

FormStack.prototype.getElement = function() {
	return this.element;
}

FormStack.prototype.build = function() {
	var form = new Form( this.model );
	form.build()
	this.element = form.getElement();	
};
