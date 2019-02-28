function _buildElement( elementType ) {
	return document.createElement( elementType );
}

function buildElement( elementType, id ) {
	var element = _buildElement( elementType );
	element.setAttribute( "id", id );
	return element;
}

function buildComponent( elementType, type, model ) {
	var element = _buildElement( elementType );
	if( type ) {
		element.setAttribute( "type", type );
	}
	for ( key in model ) {
		if ( key.substr(0, 1) != "_" ) {
			element.setAttribute(key, model[key]);
		}
	}
	return element;
}

function componentFactory( component ) {
	if( component._type == 'textfield' )
		return new TextField( component );
	if( component._type == 'label' )
		return new Label( component );
}

function createLayout( container ) {
	return new LayoutForm( container );
}