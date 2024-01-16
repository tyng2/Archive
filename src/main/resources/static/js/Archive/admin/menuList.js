$('#menuList').CMinit(function(){
	
	var $page = $('#menuList');
	
	var _view = (function(){
		
		var _init = function(){
			
			$page.on('click', '#chAll', function() {
				if ($('#chAll').is(':checked')){
					$('input:checkbox[name=chBoxId]').prop('checked', true);
				} else {
					$('input:checkbox[name=chBoxId]').prop('checked', false);
				}
			});
			
			$('table', $page).on('click', '.addMenu', function(){
				
				if ($('.add_menu_tr').length == 0) {
					let $target = $(this).closest('tr');
					let menuId	= $target.data('menu_id');
					createMenuInput($target, menuId);
					
				} else {
					Swal.fire({
						text	: '이미 추가중인 메뉴가 있습니다.',
						icon	: 'warning'
					}).then(result => {
						$('.add_menu_tr td').first().find('input').focus();
					});
				}
			});
			
			$('table', $page).on('click', '.addMenuApply', function(){
				console.log('addMenuApply click!');
				_hand.insertMenuApply();
			});
			$('table', $page).on('click', '.addMenuCancel', function(){
				console.log('addMenuCancel click!');
				$('.add_menu_tr').remove();
			});
			
			$('table', $page).on('click', '.delMenu', function(){
				let $target	= $(this).closest('tr');
				_hand.deleteMenuApply($target);
			});
			
			$page.on('click', '#addMenu', function(){
				
				if ($('.add_menu_tr').length == 0) {
					let $target = $('table tbody tr').last();
					createMenuInput($target, 0);
					
				} else {
					Swal.fire({
						text	: '이미 추가중인 메뉴가 있습니다.',
						icon	: 'warning'
					}).then(result => {
						$('.add_menu_tr td').first().find('input').focus();
					});
				}
			});
			
		};
		
		
		var createMenuInput = function($target, menuId){
			let $tr		= $('<tr>');
			let $td		= $('<td>');
			let $inp	= $('<input>').attr('type','text');
			let $btn	= $('<button>').attr('type','button').addClass('btn');
			let $trClone;
			
			menuId		= (menuId) ? menuId : 0;
			
			$trClone = $tr.clone().addClass('add_menu_tr');
			
			$td.clone().addClass('text-left').html($inp.clone().addClass('add_menu_name').css('width','100%')).appendTo($trClone);
			$td.clone().addClass('text-left').html($inp.clone().addClass('add_menu_link').css('width','100%')).appendTo($trClone);
			$td.clone().html($inp.clone().addClass('add_menu_rd').css('width','100%')).appendTo($trClone);
			$td.clone().html($inp.clone().addClass('add_menu_wr').css('width','100%')).appendTo($trClone);
			$td.clone() .append($btn.clone().addClass('btn-success btn-sm addMenuApply').text('적용'))
						.append($btn.clone().addClass('btn-custom btn-sm addMenuCancel').css('float','right').text('취소'))
						.appendTo($trClone);
			$trClone.insertAfter($target);
			
			$('#menuId').val(menuId);
		};
		
		return {
			init : _init
		};
	})();
	
	
	var _hand = (function(){
		
		var _init = function(){
			
		};
		
		var _insertMenuApply = function(){
			let menuName	= $('.add_menu_name').val();
			let menuLink	= $('.add_menu_link').val();
			let rdAuth		= $('.add_menu_rd').val();
			let wrAuth		= $('.add_menu_wr').val();
			let menuParent	= $('#menuId').val();
			
			let param = {
				'menuName'		: menuName,
				'menuLink'		: menuLink,
				'rdAuth'		: rdAuth,
				'wrAuth'		: wrAuth,
				'menuParent'	: menuParent
			};
			
			CMJS.ajax({
				url		: '/insertMenu',
				type	: 'POST',
				data	: param,
				success	: function(res){
					if (res == 1) {
						Swal.fire({
							text	: '메뉴가 추가되었습니다.',
							icon	: 'info'
						}).then(result => {
							location.reload();
						});
					} else {
						Swal.fire({
							text	: '메뉴 추가에 실패하였습니다.',
							icon	: 'warning'
						});
					}
				}
			});
		};
		
		var _deleteMenuApply = function($tr){
			let menuId = $tr.data('menu_id');
			
			let param = {
				'menuId': menuId,
			};
			
			CMJS.ajax({
				url		: '/deleteMenu',
				type	: 'POST',
				data	: param,
				success	: function(res){
					if (res == 1) {
						Swal.fire({
							text	: '메뉴가 삭제되었습니다.',
							icon	: 'info'
						}).then(result => {
							$tr.remove();
						});
					} else {
						Swal.fire({
							text	: '메뉴 삭제에 실패하였습니다.',
							icon	: 'warning'
						});
					}
				}
			});
		};
		
		return {
			init			: _init,
			insertMenuApply	: _insertMenuApply,
			deleteMenuApply	: _deleteMenuApply
		};
	})();
	
	return {
		page : $page,
		view : _view,
		hand : _hand
	};
});
