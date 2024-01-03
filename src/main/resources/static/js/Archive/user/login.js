$('#login').CMinit(function(){
	
	var $page = $('#login');
	
	var _view = (function(){
		
		var _init = function(){
			
			$page.on('click', '#naverLogin', function(){
				let param = {};
				
				if ($('#redirectURI').val()) {
					param['redirectURI'] = $('#redirectURI').val(); 
				}
				
				CMJS.submit('/naver-login', param);
			});
			
		};
		
		return {
			init : _init
		};
	})();
	
	var _hand = (function(){
		
		var _init = function(){
			
		};
		
		return {
			init : _init
		};
	})();
	
	return {
		page : $page,
		view : _view,
		hand : _hand
	};
});
