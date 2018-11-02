$("#js-login-submit").click(function() {
	let username = $("#js-login-username");
	let password = $("#js-login-password");
	
	let url = urlBuilder("basicAuth", "login", "")
	
	sendReq("POST", url, true).then(function(ret) {
		console.log(ret);
	});
});