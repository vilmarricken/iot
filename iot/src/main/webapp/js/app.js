function Application () {
	this.forms = [];
	this.actual = null;
	this.form_content = document.getElementById("div-form-content");
}

Application.prototype.addNewForm = function( _state, _model ) {
	this.addForm( new Form( _state, _model ) );
};

Application.prototype.addForm = function( _form ) {
	if( this.actual ) {
		this.form_content.removeChild( this.actual.getElement() );
	}
	this.actual = _form;
	this.forms.push( this.actual );
	this.form_content.appendChild(this.actual.getElement()); 
};

Application.prototype.closeForm = function() {
	if( this.forms.length == 0 ) {
		this.form_content.innerHTML = "&nbsp";
	} else {
		form = forms.pop();
		this.form_content.innerHTML = "&nbsp";
	}
};

function Form ( _state, _model ) {
	this.state = _state;
	this.model = _model;
	this.build();
}

Form.prototype.getElement = function() {
	return this.element;
}

Form.prototype.build = function() {
	form = document.createElement("div");
	form.innerHTML = this.state;
	this.element = form;	
};

var application;

function start() {
	application = new Application();	
}
