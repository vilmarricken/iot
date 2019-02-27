function _buildElement( elementType ) {
	return document.createElement( elementType );
}

function buildElement( elementType, id ) {
	var element = _buildElement( elementType );
	element.setAttribute( "id", id );
	return element;
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