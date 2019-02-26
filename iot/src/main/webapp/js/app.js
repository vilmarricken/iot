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
		this.forms.push( this.actual );
	}
	this.actual = _form;
	this.form_content.appendChild(this.actual.getElement()); 
};

Application.prototype.closeForm = function() {
	if( this.actual ) {
		this.form_content.removeChild( this.actual.getElement() );
	}
	if( this.forms.length == 0 ) {
		this.actual = 0;
	} else {
		this.actual = this.forms.pop();
		this.form_content.appendChild(this.actual.getElement());
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
	form.appendChild(createButton("close"));
	this.element = form;	
};

function createButton( name ) {
	element = document.createElement("input");
	element.setAttribute("type", "button");
	element.setAttribute("id", name);
	element.setAttribute("name", name);
	element.setAttribute("value", name);
	element.setAttribute("onClick", "application.closeForm()");
	return element;
}

var application;

function start() {
	application = new Application();	
}
