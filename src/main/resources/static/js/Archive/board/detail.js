var detail = (function() {
	
	var _init = function(){
		
		listComment();
	
		$('.download').on('click', function(){
			let fileId = $(this).data('file_id');
			
			common.ajax({
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
			
			common.ajax({
				url		: '/insertComment',
				type	: 'POST',
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
		
		
		$('#comment').on('click', '.delComment', function(){
			var commId = $(this).data('comm_id');
			console.log(commId);
			let param 		= {
				'commId': commId
			};
			common.ajax({
				url		: '/deleteComment',
				type	: 'POST',
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
		
		$('#commentPageBlock .custom-pagination').on('click', '.pageBtn', function(){
			var pageNum = $(this).data('page_num');
			listComment(pageNum);
		});
		
		$('#view').on('click', '.update', function(){
			let bordId	= $('#bordId').val();
			let pageNum	= $('#pageNum').val();
			
		});
		
	};
	
	function listComment(page){
		let bordId	= $('#bordId').val();
		let pageNum	= (page) ? page : $('#pageNum').val();
		
		var param	= {
			'bordId'	: bordId,
			'pageNum'	: pageNum
		};
		
		common.ajax({
			url		: '/comment',
			type	: 'GET',
			data	: param,
			success	: function(result) {
				drawComment(result.commentList, result.pageInfo.allRowCount);
				drawCommentPageBlock(result.pageInfo);
			}
		});
	};
	
	function drawComment(result, allRowCount){
		let $comment 	= $('#comment').empty();
		let $p			= $('<p>');
		let $span		= $('<span>');
		let $inp		= $('<input>').attr('type', 'hidden');
		let $a			= $('<a>');
		
		let $pClone, $spanClone;	// 일반적으로 close
		
		let boardAuth 	= document.querySelector('#boardAuth').innerHTML;
		
		$p.clone().addClass('mb-4').html('댓글 : ' + allRowCount).appendTo($comment);
		
		$.each(result, function(i, obj){
		
			$pClone = $p.clone().addClass('em').appendTo($comment);
			$('<b></b>').html(obj.nickname+'&nbsp;&nbsp;&nbsp;&nbsp;').appendTo($pClone);	
			$span.clone().addClass('smallFont').html(common.dateFormatter(obj.commDate)).appendTo($pClone);
			
			if (boardAuth == 'true'){
				$spanClone = $span.clone().attr('style', 'float: right;').appendTo($pClone);
				
				$inp.clone().attr('id', 'commId'+i).val(obj.commId).appendTo($spanClone);
				$a.clone().addClass('smallBtn delComment').data('comm_id', obj.commId).html('삭제').appendTo($spanClone);
			}
				
			$p.clone().addClass('mb-0').html(obj.commCont).appendTo($comment);
			$('<hr>').appendTo($comment);
		});
	};
	
	function drawCommentPageBlock(pageInfo){
		let $pageBlock	= $('#commentPageBlock .custom-pagination').empty();
		let $span		= $('<span>');
		let $img		= $('<img>').attr({'width':'18px','height':'18px'});
		let $a			= $('<a>').attr('href','javascript:void(0);');
		let $imgClone;
	console.log(pageInfo);
	
		if (pageInfo.allRowCount > 0) {
			
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
		}
		
	};
	
	return {
		init : _init
	};
}());
