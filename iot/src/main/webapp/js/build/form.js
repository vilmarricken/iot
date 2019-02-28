function Form( _state, _form ) {
	this.state = _state;
	this.form = _form;
	this.form.components = [];
	for( component in _form.components ) {
		this.form.components.push( componentFactory( component ) );
	}
	this.build();
}

Form.prototype.getElement = function() {
	return this.container;
}

Form.prototype.build = function() {
	this.container = createLayout( this.form ).build();
}

