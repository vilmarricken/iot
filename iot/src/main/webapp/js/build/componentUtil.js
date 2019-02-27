function buildElement( elementType, type, model ) {
	var element = document.createElement( elementType );
	element.setAttribute( "type", type );
	for ( key in model ) {
		if ( key.substr(0, 1) != "_" ) {
			element.setAttribute(key, model[key]);
		}
	}
	return element;
}