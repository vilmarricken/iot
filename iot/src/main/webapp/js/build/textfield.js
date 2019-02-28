function TextField( _textfield ) {
	this.textfield = _textfield;
	this.build();
}

TextField.prototype.getElement = function() {
	return this.element;
}

TextField.prototype.build = function() {
	this.element = buildComponent( "input", this.textfield._type, this.textfield );
}

