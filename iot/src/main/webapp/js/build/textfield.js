function TextField( _textfield ) {
	this.textfield = _textfield;
}

Button.prototype.getElement = function() {
	return this.element;
}

Button.prototype.build = function() {
	this.element = buildComponent( "input", this.textfield._type, this.textfield );
}

