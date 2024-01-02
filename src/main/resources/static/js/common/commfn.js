(function($){
	console.log('commfn');
	var $fn = $.fn;
	
	$fn.isFirstInit = 1;
	$fn.CMinit = function(initFn){
		console.log('cminit');
		
		if ($fn.isFirstInit == 1) {
			$fn.isFirstInit++;
		}
		$(document).ready(function(){
			
			if (initFn) {
				var $initObj = initFn();
				
				if ($initObj) {
					$initObj.view && $initObj.view.init && $initObj.view.init();
					$initObj.hand && $initObj.hand.init && $initObj.hand.init();
				}
				
			}
			
		});
		
	};
	
	
})(jQuery);