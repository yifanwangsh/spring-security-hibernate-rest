function urlBuilder(module, work, message) {
	const protocol = "http://";
	const host = "localhost:8080/";
	return protocol + host + module + "/" + work + "/" + message;
}

function sendReq(method, url, async) {
	return new Promise(function(resolve, reject) {
		let xhr = new XMLHttpRequest();
		xhr.open(method, url, async);
		xhr.onload = function() {
			if (xhr.status >= 200 && xhr.status < 300) {
				resolve(xhr.responseText);
			} else {
				reject(xhr.statusText);
			}
		}
		xhr.send();
	}); 
};