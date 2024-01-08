$('#update').CMinit(function(){
	
	var $page = $('#update');
	
	var _view = (function(){
		
		var _init = function(){
			
			$('#fileArea', $page).on('click', '.fileAdd', function(){
				_fileAdd();
			});
			
			$('#fileArea', $page).on('click', '.fileDel', function(){
				_fileDelete();
			});
			
			$('#fileArea', $page).on('click', '.delete', function(){
				let fileId = $(this).data('file_id');
				
				if (confirm('이 파일을 삭제합니까?')) {
					_hand.deleteFile(fileId);
				}
			});
			
		};
		
		var _fileAdd = function() {
//			var size = 50;
			var filecountTemp	= parseInt(document.getElementById("file_cnt").value);
			var fileCnt			= $('.delete').length;
			if (filecountTemp + fileCnt == 10) {
				alert("파일 업로드는 최대 10개까지 가능합니다.");
				return;
			} else {
				var parents	= document.getElementById("file_add_form");
				var br		= document.createElement("br");
				br.setAttribute("id", "br" + (filecountTemp + 1));
				parents.appendChild(br);
				
				var obj		= document.createElement("input");
				obj.setAttribute("type", "file");
//				obj.setAttribute("size", size);
				obj.setAttribute("name", "mFile");
				obj.setAttribute("class", "form-control");
				obj.setAttribute("id", "file" + (filecountTemp + 1));
				parents.appendChild(obj);
				document.getElementById("file_cnt").value = filecountTemp + 1;
			}
		};
		
		var _fileDelete = function() {
			var filecountTemp = parseInt(document.getElementById("file_cnt").value);
			if (filecountTemp > 1) {
				var parents	= document.getElementById("file_add_form");
				var obj		= document.getElementById("file" + filecountTemp);
				var br		= document.getElementById('br' + filecountTemp);
				parents.removeChild(obj);
				parents.removeChild(br);
				document.getElementById("file_cnt").value = filecountTemp - 1;
			}
		};
		
		return {
			init : _init
		};
	})();
	
	var _hand = (function(){
		
		var _init = function(){
			
		};
		
		var _deleteFile = function(fileId){
			CMJS.ajax({
				url		: '/deleteFile',
				type	: 'POST',
				data	: {
					fileId : fileId
				},
				success	: function(result){
					if (result == 1) {
						$('#fileA'+fileId).remove();
						let fileSize = $('#file_size').val();
						$('#file_size').val(fileSize - 1);
					} else {
						alert('fail!');
					}
				}
			});
		};
		
		return {
			init		: _init,
			deleteFile	: _deleteFile
		};
	})();
	
	return {
		page : $page,
		view : _view,
		hand : _hand
	};
});
