$('#index').CMinit(function(){
	
	var $page = $('#index');
	
	var _view = (function(){
		
		var _init = function(){
			
			$('#google').click(function() {
				$('#searchNAVER').attr('style', 'display:none;');
				$('#searchGoogle').removeAttr('style');
			});
			
			$('#naver').click(function() {
				$('#searchGoogle').attr('style', 'display:none;');
				$('#searchNAVER').removeAttr('style');
			});
			
			$('#addSite').click(function() {
				$addSite	= $('#addSite').attr('disabled', 'disabled');
				
				var name	= $('#name').val();
				var url		= $('#url').val();
				
				var param = {
					'name'	: name,
					'url'	: url
				};
				CMJS.ajax({
					url		: '/insertSite',
					type	: 'post',
					data	: param,
					success	: function() {
						_hand.listSite();
						var name	= document.querySelector('#name');
						name.value	= '';
						var url		= document.querySelector('#url');
						url.value	= '';
					}
				});
				$addSite = $('#addSite').removeAttr('disabled');
			});
			
			$page.on('click', '.deleteSite', function(){
				let key = $(this).data('site_key');
				_hand.delSite(key);
			});
			
		};
		
		return {
			init : _init
		};
	})();
	
	
	var _hand = (function(){
		
		var _init = function(){
			var typed = new Typed('.typed-words', {
				strings 	: [ 'C', ' Java', ' Python', ' SQL', ' Web' ],
				typeSpeed 	: 80,
				backSpeed 	: 80,
				backDelay 	: 4000,
				startDelay	: 1000,
				loop 		: true,
				showCursor 	: true
			});
			
			_listSite();
		};
		
		
		var _listSite = function(){
			CMJS.ajax({
				url: '/siteList',
				success: function(result) {
					
					var $siteList	= $('#siteList').empty();
					var $h2 		= $('<h2>');
					var $a			= $('<a>');
					var $inp		= $('<input>').attr('type','hidden');
					var $img		= $('<img>').attr('src','images/close.png').attr('width','18px;');
					var $h2Clone;
					
					for (var i in result) {
						$h2Clone = $h2.clone().appendTo($siteList);
						$a.clone().attr('href',result[i].siteUrl).attr('target','_blank').attr('style','margin-right:2rem;').html(result[i].siteName).appendTo($h2Clone);
						$inp.clone().attr('id','siteId'+i).val(result[i].siteId).appendTo($h2Clone);
						$a.clone().attr('data-site_key',i).addClass('deleteSite').css('cursor','pointer').html($img.clone()).appendTo($h2Clone);
					}
				},
				error: function(xhr, status, error){
					console.log(xhr, status, error);
				}
			});
		};
		
		var _delSite = function(i){
			var id = '#siteId' + i;
			var siteId = $(id).val();
			console.log(siteId);
			var param = {
				'siteId': siteId	
			};
			CMJS.ajax({
				url		: '/deleteSite',
				type	: 'post',
				data	: param,
				success	: function() {
					_listSite();
				}
			});
			return false;
		};
		
		return {
			init		: _init,
			listSite	: _listSite,
			delSite		: _delSite
		};
	})();
	
	return {
		page : $page,
		view : _view,
		hand : _hand
	};
});
