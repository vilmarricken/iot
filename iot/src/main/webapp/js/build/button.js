function Button( _button ) {
	this.button = _button;
}

Button.prototype.getElement = function() {
	return this.element;
}

Button.prototype.build = function() {
	this.element = buildComponent( "input", this.button._type, this.button );
}

