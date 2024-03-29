$('#detail').CMinit(function(){
	
	var $page = $('#detail');
	
	var _view = (function(){
		
		var _init = function(){
		
			$('.download', $page).on('click', function(){
				let fileId = $(this).data('file_id');
				
				CMJS.ajax({
					url: 'download',
					type: 'POST',
					data: {
						fileId : fileId
					},
					success: function(data){
						_fileDownload(data, fileId);
					}
				});
			});
			
			$('#insertComment').click(function() {
				$insertComment 	= $('#insertComment').attr('disabled', 'disabled');
				let $commCont 	= $('#commCont');
				let bordId		= $('#bordId').val();
				let commCont	= $commCont.val();
				
				if (!commCont) {
					Swal.fire({
						text	: '내용이 없습니다.',
						icon	: 'warning'
					}).then(result => {
						$commCont.focus();
					});
					
				} else {
					let param 	= {
						'bordId'	: bordId,
						'commCont'	: commCont
					};
					
					CMJS.ajax({
						url		: '/insertComment',
						type	: 'POST',
						data	: param,
						success	: function(data) {
							if (data > 0) {
								$commCont.val('');
								_hand.listComment();
							} else {
								Swal.fire({
									text	: 'something wrong',
									icon	: 'warning'
								});
							}
						}
					});
					$insertComment = $('#insertComment').removeAttr('disabled');
				}
			});
			
			$('#comment').on('click', '.delComment', function(){
				var commId = $(this).data('comm_id');
				console.log(commId);
				let param 		= {
					'commId': commId
				};
				CMJS.ajax({
					url		: '/deleteComment',
					type	: 'POST',
					data	: param,
					success	: function(data) {
						if (data > 0) {
							_hand.listComment();
						} else {
							Swal.fire({
								text	: 'something wrong',
								icon	: 'warning'
							});
						}
					}
				});
				return false;
			});
			
			$('#commentPageBlock .custom-pagination').on('click', '.pageBtn', function(){
				var pageNum = $(this).data('page_num');
				_hand.listComment(pageNum);
			});
			
			$('#view', $page).on('click', '.update', function(){
				var param = {
					bordId	: $('#bordId').val(),
					userId	: $('#userId').val(),
					pageNum	: $('#pageNum').val()
				};
				CMJS.submit('/update', param, 'POST');
			});
			
			$('#view', $page).on('click', '.delete', function(){
				if (confirm('정말 삭제하시겠습니까?')) {
					var param = {
						bordId	: $('#bordId').val(),
						userId	: $('#userId').val(),
						category: $('#category').val(),
						pageNum	: $('#pageNum').val()
					};
					CMJS.submit('/deleteBoard', param, 'POST');
				}
			});
			
			$('#view', $page).on('click', '.listBtn', function(){
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
				let qs			= CMJS.getQueryString(param);
				location.href	= '/board' + qs + '&#title';
			});
			
			
		};
		
		var _fileDownload = function(dateStr, fileId){
			location.href = 'download?dateStr='+dateStr+'&fileId='+fileId;
		};
		
		return {
			init : _init
		};
		
	})();
	
	
	var _hand = (function(){
		
		var _init = function(){
			_listComment();
		};
		
		var _listComment = function(page){
			let bordId	= $('#bordId').val();
			let pageNum	= (page) ? page : 0;
			
			let param	= {
				'bordId'	: bordId,
				'pageNum'	: pageNum
			};
			
			CMJS.ajax({
				url		: '/comment',
				type	: 'GET',
				data	: param,
				success	: function(result) {
					_drawComment(result.commentList, result.pageInfo.allRowCount);
					_drawCommentPageBlock(result.pageInfo);
				}
			});
		};
		
		var _drawComment = function(result, allRowCount){
			let $comment 	= $('#comment').empty();
			let $p			= $('<p>');
			let $span		= $('<span>');
			let $inp		= $('<input>').attr('type', 'hidden');
			let $a			= $('<a>');
			
			let $pClone, $spanClone;	// 일반적으로 close
			
			$p.clone().addClass('mb-4').html('댓글 : ' + allRowCount).appendTo($comment);
			
			$.each(result, function(i, obj){
			
				$pClone = $p.clone().addClass('em').appendTo($comment);
				$('<b></b>').html(obj.nickname+'&nbsp;&nbsp;&nbsp;&nbsp;').appendTo($pClone);	
				$span.clone().addClass('smallFont').html(CMJS.dateFormatter(obj.commDate)).appendTo($pClone);
				
				if (obj.isCreated == 1){
					$spanClone = $span.clone().attr('style', 'float: right;').appendTo($pClone);
					
					$inp.clone().attr('id', 'commId'+i).val(obj.commId).appendTo($spanClone);
					$a.clone().addClass('smallBtn delComment').data('comm_id', obj.commId).html('삭제').appendTo($spanClone);
				}
					
				$p.clone().addClass('mb-0').html(obj.commCont).appendTo($comment);
				$('<hr>').appendTo($comment);
			});
		};
		
		var _drawCommentPageBlock = function(pageInfo){
			let $pageBlock	= $('#commentPageBlock .custom-pagination').empty();
			let $span		= $('<span>');
			let $img		= $('<img>').attr({'width':'18px','height':'18px'});
			let $a			= $('<a>').attr('href','javascript:void(0);');
			let $imgClone;
console.log(pageInfo);
		
			if (pageInfo.allRowCount > 0) {
				$('#commentPageBlock').show();
				
				if (pageInfo.startPage > pageInfo.pageBlockSize) {
					$imgClone = $img.clone().attr('src','images/left-arrow.png');
					$pageBlock.append($a.clone().addClass('pageBtn').data('page_num',pageInfo.startPage-1).html($span.clone().addClass('pt').html($imgClone)));
				}
				for(var i = pageInfo.startPage; i <= pageInfo.endPage; i++){
					if (i == pageInfo.pageNum) {
						$pageBlock.append($span.clone().html(i));
					} else {
						$pageBlock.append($a.clone().addClass('pageBtn').data('page_num',i).html(i));
					}
				}
				if (pageInfo.endPage < pageInfo.maxPage) {
					$imgClone = $img.clone().attr('src','images/right-arrow.png');
					$pageBlock.append($a.clone().addClass('pageBtn').data('page_num',pageInfo.endPage+1).html($span.clone().addClass('pt').html($imgClone)));
				}
			} else {
				$('#commentPageBlock').hide();
			}
			
		};
		
		return {
			init		: _init,
			listComment	: _listComment
		};
	})();
	
	return {
		page : $page,
		view : _view,
		hand : _hand
	};
});
