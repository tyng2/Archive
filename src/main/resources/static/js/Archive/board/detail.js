$(function() {
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
		let $comm_cont 	= $('#comm_cont');
		let bord_id 	= $('#bord_id').val();
		let comm_cont 	= $comm_cont.val();
		
		let param 		= {
			'bord_id'	: bord_id,
			'comm_cont'	: comm_cont
		};
		
		ajaxAction({
			url		: 'insertComment.do',
			type	: 'post',
			data	: param,
			success	: function() {
				$comm_cont.val('');
				listComment();
			}
		});
		$insertComment = $('#insertComment').removeAttr('disabled');
	});
	
	
	function fileDownload(dateStr, fileId){
		location.href = 'download?dateStr='+dateStr+'&fileId='+fileId;
	}
	
});

function delComment(i){
	let comm_id 	= $('#comm_id'+i).val();
	let param 		= {
		'comm_id': comm_id
	};
	ajaxAction({
		url		: 'deleteComment.do',
		data	: param,
		success	: function() {
			listComment();
		}
	});
	return false;
}

function listComment(){
	let bord_id = $('#bord_id').val();
	
	ajaxAction({
		url: 'comment?bord_id='+bord_id,
		success: function(result) {
			let $comment 	= $('#comment').empty();
			let $p			= $('<p>');
			let $span		= $('<span>');
			let $inp		= $('<input>').attr('type', 'hidden');
			let $a			= $('<a>');
			
			let $pClone, $spanClone;	// 일반적으로 close
			
			let adminId 	= 'admin';
			let sessionID 	= document.querySelector('#sessionID').innerHTML.trim();
			
			$p.clone().addClass('mb-4').html('댓글 : ' + result.length).appendTo($comment);
			
			$.each(result, function(i, obj){
			
				$pClone = $p.clone().addClass('em').appendTo($comment);
				$('<b></b>').html(obj.user_id+'&nbsp;&nbsp;&nbsp;&nbsp;').appendTo($pClone);	
				$span.clone().addClass('smallFont').html(dateFormatter(obj.comm_date)).appendTo($pClone);
				
				if (obj.user_id === sessionID || adminId === sessionID){
					$spanClone = $span.clone().attr('style', 'float: right;').appendTo($pClone);
					
					$inp.clone().attr('id', 'comm_id'+i).val(obj.comm_id).appendTo($spanClone);
					$a.clone().addClass('smallBtn').attr('onclick', 'delComment('+i+')').html('삭제').appendTo($spanClone);
					
				}
					
				$p.clone().addClass('mb-0').html(obj.comm_cont).appendTo($comment);
				$('<hr>').appendTo($comment);
			});
		}
	});
}