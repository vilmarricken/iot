function Label( _label ) {
	this.label = _label;
	build();
}

Label.prototype.getElement = function() {
	return this.element;
}

Label.prototype.build = function() {
	this.element = buildComponent( "span" );
	this.element.innetHTML = this.label.label;
}

