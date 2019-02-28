function buildElement( elementType ) {
	return document.createElement( elementType );
}

function buildComponent( elementType, type, model ) {
	var element = _buildElement()
	element.setAttribute( "type", type );
	for ( key in model ) {
		if ( key.substr(0, 1) != "_" ) {
			element.setAttribute(key, model[key]);
		}
	}
	return element;
}

function createLayout( layout ) {
	return LayoutForm();
}