$(function(){
	
	console.log('call layout');
	let path = window.location.pathname;
	path = (path.length > 0) ? path.substr(1) : '';
	
console.log(path+'.init(); EXECUTE');
	if (path) {
		let isExistJs = new Function('return typeof '+path)();
console.log(isExistJs);
		if (isExistJs && isExistJs == 'object'){
			let isExistFn = new Function('return typeof('+path+'.init) == "function"');
console.log(isExistFn());
			if (isExistFn()) {
				new Function(path+'.init();')();
			}
		}
	}

	
	
	
});