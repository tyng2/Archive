var detail = (function() {
	
	var _init = function(){
		
		listComment();
	
		$('.download').on('click', function(){
			let fileId = $(this).data('file_id');
			
			ajaxAction({
				url: 'download',
				type: 'POST',
				data: {
					fileId : fileId
				},
				success: function(data){
					fileDownload(data, fileId);
				}
			});
		});
		
		$('#insertComment').click(function() {
			$insertComment 	= $('#insertComment').attr('disabled', 'disabled');
			let $commCont 	= $('#commCont');
			let bordId		= $('#bordId').val();
			let commCont	= $commCont.val();
			let pageNum		= $('#pageNum').val();
			
			if (!commCont) {
				alert('No content!');
				return false;
			}
			
			let param 		= {
				'bordId'	: bordId,
				'commCont'	: commCont,
				'pageNum'	: pageNum
			};
			
			ajaxAction({
				url		: '/insertComment',
				type	: 'post',
				data	: param,
				success	: function(data) {
					if (data > 0) {
						$commCont.val('');
						listComment();
					} else {
						alert('something wrong');
					}
				}
			});
			$insertComment = $('#insertComment').removeAttr('disabled');
		});
		
		function fileDownload(dateStr, fileId){
			location.href = 'download?dateStr='+dateStr+'&fileId='+fileId;
		}
		
		
		$('.delComment').on('click', function(){
			var commId = $(this).data('comm_id');
			let param 		= {
				'commId': commId
			};
			ajaxAction({
				url		: '/deleteComment',
				data	: param,
				success	: function(data) {
					if (data > 0) {
						listComment();
					} else {
						alert('something wrong');
					}
				}
			});
			return false;
		});
	};
	
//	function delComment(i){
//		let commId 	= $('#commId'+i).val();
//		let param 		= {
//			'commId': commId
//		};
//		ajaxAction({
//			url		: '/deleteComment',
//			data	: param,
//			success	: function(data) {
//				if (data > 0) {
//					listComment();
//				} else {
//					alert('something wrong');
//				}
//			}
//		});
//		return false;
//	}
	
	function listComment(){
		let bordId = $('#bordId').val();
		
		ajaxAction({
			url: '/comment?bordId='+bordId,
			success: function(result) {
				drawComment(result.commentList);
				drawCommentPageBlock(result.pageInfo);
			}
		});
	};
	
	function drawComment(result){
		let $comment 	= $('#comment').empty();
		let $p			= $('<p>');
		let $span		= $('<span>');
		let $inp		= $('<input>').attr('type', 'hidden');
		let $a			= $('<a>');
		
		let $pClone, $spanClone;	// 일반적으로 close
		
		let boardAuth 	= document.querySelector('#boardAuth').innerHTML;
		
		$p.clone().addClass('mb-4').html('댓글 : ' + result.length).appendTo($comment);
		
		$.each(result, function(i, obj){
		
			$pClone = $p.clone().addClass('em').appendTo($comment);
			$('<b></b>').html(obj.nickname+'&nbsp;&nbsp;&nbsp;&nbsp;').appendTo($pClone);	
			$span.clone().addClass('smallFont').html(dateFormatter(obj.commDate)).appendTo($pClone);
			
			if (boardAuth){
				$spanClone = $span.clone().attr('style', 'float: right;').appendTo($pClone);
				
				$inp.clone().attr('id', 'commId'+i).val(obj.commId).appendTo($spanClone);
				$a.clone().addClass('smallBtn delComment').data('comm_id', i).html('삭제').appendTo($spanClone);
			}
				
			$p.clone().addClass('mb-0').html(obj.commCont).appendTo($comment);
			$('<hr>').appendTo($comment);
		});
	};
	
	function drawCommentPageBlock(result){
		
	};
	
	return {
		init : _init
	};
}());
detail.init();