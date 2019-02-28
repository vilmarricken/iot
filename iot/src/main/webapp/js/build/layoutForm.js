function LayoutForm( _container ) {
	this.container = _container;
}

LayoutForm.prototype.getElement = function() {
	return this.element;
}

LayoutForm.prototype.build = function() {
	this.element = buildElement( "div" );
	for( component in this.container.components ) {
		this.element.appendChild( this.container.components[component].getElement() );
	}
}

