masterRequest = function(request) {
	if(!request.error){
		request.error = materRequestErrorDefault;
	}
	if(!request.success){
		request.success = materRequestSuccessDefault;
	}
	$.ajax(request);
}

materRequestErrorDefault = function(xhr, status, error){
	alert('Error: ' + error);
}

materRequestSuccessDefault = function(result,status,xhr){
	alert('Success: ' + result);
}