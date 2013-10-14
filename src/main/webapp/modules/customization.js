/*
 * Queries the server and launches an "customization" event
 */
define([ "message-bus", "communication" ], function(bus) {

	var customizationInfo = {};

	bus.publish("ajax", {
		dataType : "json",
		url : "customization",
		success : function(data, textStatus, jqXHR) {
			for ( var attr in data) {
				if (data.hasOwnProperty(attr)) {
					customizationInfo[attr] = data[attr];
				}
			}
			document.title = customizationInfo.title;
			bus.publish("customization-received", customizationInfo);
		},
		errorMsg : "Cannot initialize application"
	});

	return customizationInfo;
});