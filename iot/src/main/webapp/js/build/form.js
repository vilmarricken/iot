function Form( _state, _model ) {
	this.state = _state;
	this.model = _model;
	this.components = [];
	for( component in this.model.components ) {
		this.components.push( componentFactory( this.model.components[component] ) );
	}
	this.build();
}

Form.prototype.getElement = function() {
	return this.layout.getElement();
}

Form.prototype.build = function() {
	this.layout = createLayout( this );
	this.layout.build();
}

