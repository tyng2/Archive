$('#board').CMinit(function(){
	
	var $page = $('#board');
	
	var _view = (function(){
		
		var _init = function(){
			
			$('.custom-pagination', $page).on('click', '.pageBtn', function(){
				let category	= $('#category').val();
				let search		= $('#search').val();
				let pageNum		= $(this).data('page');
				let param		= {
					'pageNum' : pageNum
				};
				if (category) {
					param['category']	= category;
				}
				if (search) {
					param['search']		= search;
				}
				let qs			= CMJS.getQueryString(param);
				location.href	= '/board' + qs + '&#title';
			});
			
			$('#boardContent', $page).on('click', '.catgBtn', function(){
				let category	= $(this).data('catg');
				let param		= {
					'category' : category
				};
				let qs			= CMJS.getQueryString(param);
				location.href	= '/board' + qs + '&#title';
			});
			
			$('#boardContent', $page).on('click', '.detailBtn', function(){
				let bordId		= $(this).data('bord_id');
				let category	= $('#category').val();
				let search		= $('#search').val();
				let pageNum		= $('#pageNum').val();
				let param		= {
					'bordId' : bordId
				};
				if (category) {
					param['category']	= category;
				}
				if (search) {
					param['search']		= search;
				}
				if (pageNum) {
					param['pageNum']	= pageNum;
				}
				let qs			= CMJS.getQueryString(param);
				location.href	= '/detail' + qs + '&#view';
			});
			
			$page.on('click', '.write', function(){
				let category	= $('#category').val();
				let search		= $('#search').val();
				let pageNum		= $('#pageNum').val();
				let param		= {};
				if (category) {
					param['category']	= category;
				}
				if (search) {
					param['search']		= search;
				}
				if (pageNum) {
					param['pageNum']	= pageNum;
				}
				CMJS.submit('/write', param);
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
