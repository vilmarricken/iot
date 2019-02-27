function Form( _form ) {
	this.form = _form;
	this.form.components = [];
	for( component in _form.components ) {
		this.form.components.push( componentFactory( component ) );
	}
}

Form.prototype.getElement = function() {
	return this.container;
}

Form.prototype.build = function() {
	this.container = buildElement( "div", this.form.id );
	for( component in this.form.components ) {
		
	}
}

