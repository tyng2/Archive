$('#menuList').CMinit(function(){
	
	var $page = $('#menuList');
	
	var _view = (function(){
		
		var _init = function(){
			/*
			$page.on('click', '#chAll', function() {
				if ($('#chAll').is(':checked')){
					$('input:checkbox[name=chBoxId]').prop('checked', true);
				} else {
					$('input:checkbox[name=chBoxId]').prop('checked', false);
				}
			});
			*/
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
			
			$('table', $page).on('click', '.delMenu', function(){
				let $target	= $(this).closest('tr');
				_hand.deleteMenuApply($target);
			});
			
			$('table', $page).on('click', '.addMenuApply', function(){
				console.log('addMenuApply click!');
				_hand.insertMenuApply();
			});
			$('table', $page).on('click', '.addMenuCancel', function(){
				console.log('addMenuCancel click!');
				$('.add_menu_tr').remove();
			});
			
			$page.on('click', '#addMenu', function(){
				
				if ($('.add_menu_tr').length == 0) {
					let $target = $('table tbody tr').last();
					createMenuInput($target, null);
					
				} else {
					Swal.fire({
						text	: '이미 추가중인 메뉴가 있습니다.',
						icon	: 'warning'
					}).then(result => {
						$('.add_menu_tr td').first().find('input').focus();
					});
				}
			});
			
			$page.on('click', '#modMenu', function(){
				let $chkMenu = $('input[name="chkMenu"]:checked');
				
				if ($chkMenu.length < 1) {
					Swal.fire({
						text	: '수정할 메뉴를 선택하세요.',
						icon	: 'warning'
					});
					
				} else {
					
					if ($('.mod_menu_tr').length == 0){
						let $tr		= $chkMenu.closest('tr');
						modifyMenuInput($tr, null);
						
					} else {
						Swal.fire({
							text	: '이미 수정중인 메뉴가 있습니다.',
							icon	: 'warning'
						}).then(result => {
							$('.mod_menu_tr td').first().find('input').focus();
						});
					}
				}
			});
			
			$('table', $page).on('click', '.modMenuApply', function(){
				console.log('modMenuApply click!');
				_hand.modifyMenuApply();
			});
			$('table', $page).on('click', '.modMenuCancel', function(){
				console.log('modMenuCancel click!');
				$('.mod_tr').removeClass('mod_tr').show();
				$('.mod_menu_tr').remove();
			});
			
			$page.on('click', '.orderBtn', function(){
				let $chkMenu = $('input[name="chkMenu"]:checked');
				
				if ($chkMenu.length < 1) {
					Swal.fire({
						text	: '수정할 메뉴를 선택하세요.',
						icon	: 'warning'
					});
					
				} else {
					let orderFlag	= $(this).data('order_flag');
					let $chk		= $chkMenu.closest('tr');
					_hand.modifyMenuOrderApply($chk, orderFlag);
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
			
			$td.clone().appendTo($trClone);
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
		
		var modifyMenuInput = function($target){
			let $tr		= $('<tr>');
			let $td		= $('<td>');
			let $inp	= $('<input>').attr('type','text');
			let $btn	= $('<button>').attr('type','button').addClass('btn');
			let $trClone;
			
			let menuId	= $target.data('menu_id');
			
			let name	= CMJS.nvl($target.find('td').eq(1).text()).replaceAll('└','').trim();
			let link	= CMJS.nvl($target.find('td').eq(2).text());
			let rd		= CMJS.nvl($target.find('td').eq(3).text());
			let wr		= CMJS.nvl($target.find('td').eq(4).text());
			
			$trClone	= $tr.clone().addClass('mod_menu_tr');
			$td.clone().appendTo($trClone);
			$td.clone().addClass('text-left').html($inp.clone().addClass('mod_menu_name').css('width','100%').val(name)).appendTo($trClone);
			$td.clone().addClass('text-left').html($inp.clone().addClass('mod_menu_link').css('width','100%').val(link)).appendTo($trClone);
			$td.clone().html($inp.clone().addClass('mod_menu_rd').css('width','100%').val(rd)).appendTo($trClone);
			$td.clone().html($inp.clone().addClass('mod_menu_wr').css('width','100%').val(wr)).appendTo($trClone);
			$td.clone() .append($btn.clone().addClass('btn-success btn-sm modMenuApply').text('적용'))
						.append($btn.clone().addClass('btn-custom btn-sm modMenuCancel').css('float','right').text('취소'))
						.appendTo($trClone);
			$trClone.insertAfter($target);
			$('#menuId').val(menuId);
			
			$target.addClass('mod_tr').hide();
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
		
		var _modifyMenuApply = function(){
			let menuName	= $('.mod_menu_name').val();
			let menuLink	= $('.mod_menu_link').val();
			let rdAuth		= $('.mod_menu_rd').val();
			let wrAuth		= $('.mod_menu_wr').val();
			let menuId		= $('#menuId').val();
			
			let param = {
				'menuName'	: menuName,
				'menuLink'	: menuLink,
				'rdAuth'	: rdAuth,
				'wrAuth'	: wrAuth,
				'menuId'	: menuId
			};
			
			CMJS.ajax({
				url		: '/modifyMenu',
				type	: 'POST',
				data	: param,
				success	: function(res){
					if (res == 1) {
						Swal.fire({
							text	: '메뉴 수정이 완료되었습니다.',
							icon	: 'info'
						}).then(result => {
							location.reload();
						});
					} else {
						Swal.fire({
							text	: '메뉴 수정에 실패하였습니다.',
							icon	: 'warning'
						});
					}
				}
			});
		};
		
		
		var _modifyMenuOrderApply = function($chk, orderFlag){
			
			let menuIdChk		= $chk.data('menu_id');
			let menuOrderChk	= $chk.data('menu_order');
			let menuParentChk	= $chk.data('menu_parent');
			let $target;
			
			if (orderFlag == 'u') {
				$target = $chk.prev('tr');
			} else if (orderFlag == 'd') {
				$target = $chk.next('tr');
			}
			
			if ($target.length > 0) {
				while(menuParentChk != $target.data('menu_parent')){
					if (orderFlag == 'u') {
						$target = $target.prev('tr');
					} else if (orderFlag == 'd') {
						$target = $target.next('tr');
					}
					if ($target.length == 0) {
						break;
					}
				}
				
				if ($target.length > 0) {
					let menuIdTar		= $target.data('menu_id');
					let menuOrderTar	= $target.data('menu_order');
//					let menuParentTar	= $target.data('menu_parent');
					
					let param = {
						'menuIdChk'		: menuIdChk,
						'newOrderChk'	: menuOrderTar,
						'menuIdTar'		: menuIdTar,
						'newOrderTar'	: menuOrderChk
					};
					CMJS.ajax({
						url		: '/modifyMenuOrder',
						type	: 'POST',
						data	: param,
						success	: function(res){
							if (res == 2) {
								Swal.fire({
									text	: '순서가 변경되었습니다.',
									icon	: 'info'
								}).then(result => {
									location.reload();
								});
							} else {
								Swal.fire({
									text	: '순서 변경에 실패하였습니다.',
									icon	: 'warning'
								});
							}
						}
					});
				
				} else {
					let txt = '';
					if (orderFlag == 'u') {
						txt = '최상단 메뉴입니다.';
					} else if (orderFlag == 'd') {
						txt = '최하단 메뉴입니다.';
					}
					Swal.fire({
						text	: txt,
						icon	: 'warning'
					});
				}
				
			} else {
				let txt = '';
				if (orderFlag == 'u') {
					txt = '최상단 메뉴입니다.';
				} else if (orderFlag == 'd') {
					txt = '최하단 메뉴입니다.';
				}
				Swal.fire({
					text	: txt,
					icon	: 'warning'
				});
			}
			
		};
		
		
		return {
			init					: _init,
			insertMenuApply			: _insertMenuApply,
			deleteMenuApply			: _deleteMenuApply,
			modifyMenuApply			: _modifyMenuApply,
			modifyMenuOrderApply	: _modifyMenuOrderApply
		};
	})();
	
	return {
		page : $page,
		view : _view,
		hand : _hand
	};
});

