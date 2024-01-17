$('#userList').CMinit(function(){
	
	var $page = $('#userList');
	
	var _view = (function(){
		
		var _init = function(){
			
			$('#chAll').click(function() {
				if ($('#chAll').is(':checked')){
					$('input:checkbox[name=chBoxId]').prop('checked', true);
				} else {
					$('input:checkbox[name=chBoxId]').prop('checked', false);
				}
			});
			
			$('#userDetailModal').on('show.bs.modal', function(event){
				let $btn		= $(event.relatedTarget);
				let userId		= $btn.data('user_id');
//				let $modal	= $(this);
				console.log(userId);
				
				CMJS.ajax({
					url		: '/getUser',
					type	: 'POST',
					data	: {
						'userId' : userId
					},
					success	: function(data) {
						setModalData(data);
					}
				});
			});
			
			$('#userDetailModal').on('hide.bs.modal', function(){
				modalModifyFormCtrl(true);
			});
			
			
			$('#modifyUser').on('click', function(){
				modalModifyFormCtrl(false);
			});
			
			$('#modifyUserApply').on('click', function(){
				let param		= getInpParam($('#userModalForm'));
				param['userId']	= $('#user_id_modal').val();
				
				modalModifyFormCtrl(true);
				
				CMJS.ajax({
					url		: '/updateUser',
					type	: 'POST',
					data	: param,
					success	: function(data) {
						if (data == 1) {
							location.reload();
						} else {
							$('#userDetailModal').modal('hide');
						}
					}
				});
				
			});
			
		};
		
		var setModalData = function(data){
			let $authSelect = $('#auth_modal').empty();
			let $option		= $('<option>');
			let $optClone;
			
			$('#userModalLabel').html('User : ' + data.user.userId);
			$('#user_id_modal').val(data.user.userId);
			$('#user_txid_modal').val(data.user.userTxid);
			$('#username_modal').val(data.user.userName);
			$('#email_modal').val(data.user.email);
			$('#nickname_modal').val(data.user.nickname);
			$('#sns_type_modal').val(data.user.snsType);
			$('#create_date_modal').val(CMJS.timestampFormatter(data.user.createDate));
			$('#modify_date_modal').val(CMJS.timestampFormatter(data.user.modifyDate));
			
			if (data.authList && data.authList.length > 0) {
				$.each(data.authList, function(i, v){
					$optClone = $option.clone().val(v.authId).html(v.authName);
					if (data.user.authId == v.authId) {
						$optClone.prop('selected', true);
					}
					$optClone.appendTo($authSelect);
				});
			}
		};
		
		var modalModifyFormCtrl = function(tf){
			$('#userModalForm').find('.editable').prop('disabled', tf);
			if (tf) {
				$('#modifyUser').show();
				$('#modifyUserApply').hide();
			} else {
				$('#modifyUser').hide();
				$('#modifyUserApply').show();
			}
		};
		
		var getInpParam = function($area){
			let param	= {};
			
			$area.find('.editable').each(function() {
				$this	= $(this);
				name 	= $this.attr('name');
				value	= $this.val();
				if (value == '') {
					param = 1;
					Swal.fire({
						text	: name + ' 항목을 입력하세요.',
						icon	: 'info'
					});
					return false;
				}
				param[name]	= value;
			});
			return param;
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

