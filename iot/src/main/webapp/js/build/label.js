function Label( _label ) {
	this.label = _label;
	this.build();
}

Label.prototype.getElement = function() {
	return this.element;
}

Label.prototype.build = function() {
	this.element = buildComponent( "span" );
	this.element.innerHTML = this.label.label;
}

