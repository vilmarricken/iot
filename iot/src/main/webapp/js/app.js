function Application () {
	this.forms = [];
	this.actual = null;
	this.form_content = document.getElementById("div-form-content");
}

Application.prototype.addNewForm = function( _element, _state, _model ) {
	this.addForm( new Form( _element, _state, _model ) );
};

Application.prototype.addForm = function( _form ) {
	actual = _form;
	forms.push( actual );
};

Application.prototype.closeForm = function() {
	if( this.forms.length == 0 ) {
		this.form_content.innerHTML = "&nbsp";
	} else {
		
	}
};

function Form (_element, _state, _model) {
	this.element = _element;
	this.state = _state;
	this.model = _model;
}

Form.prototype.build = function() {
	
};

var application = new Application();