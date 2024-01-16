$('#write').CMinit(function(){
	
	var $page = $('#write');
	
	var _view = (function(){
		
		var _init = function(){
			$('#fileArea').on('click', '.fileAdd', function(){
				_fileAdd();
			});
			
			$('#fileArea').on('click', '.fileDel', function(){
				_fileDelete();
			});
			
			$page.on('click', '.listBtn', function(){
				console.log('listBtn');
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
				CMJS.submit('/board', param);
			});
		};
		
		var _fileAdd = function() {
//			var size = 50;
			var filecountTemp	= parseInt(document.getElementById("file_cnt").value);
			if (filecountTemp == 10) {
				Swal.fire({
					text	: '파일 업로드는 최대 10개까지 가능합니다.',
					icon	: 'info'
				});
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
			}
			document.getElementById("file_cnt").value = filecountTemp + 1;
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
