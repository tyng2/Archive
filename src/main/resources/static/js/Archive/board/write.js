var write = (function() {
	
	var _init = function(){
		
		$('#fileArea').on('click', '.fileAdd', function(){
			file_add();
		});
		
		$('#fileArea').on('click', '.fileDel', function(){
			file_delete();
		});
		
		
	};
	
	
	function file_add() {
//		var size = 50;
		var filecountTemp = parseInt(document.getElementById("file_cnt").value);
		var parents = document.getElementById("file_add_form");
		var br = document.createElement("br");
		br.setAttribute("id", "br" + (filecountTemp + 1));
		parents.appendChild(br);
		if (filecountTemp == 10) {
			alert("파일 업로드는 최대 10개까지 가능합니다.");
			return;
		} else {
			var obj = document.createElement("input");
			obj.setAttribute("type", "file");
			//obj.setAttribute("size", size);
			obj.setAttribute("name", "mFile");
			obj.setAttribute("class", "form-control");
			obj.setAttribute("id", "file" + (filecountTemp + 1));
			parents.appendChild(obj);
		}
		document.getElementById("file_cnt").value = filecountTemp + 1;
	};
	
	function file_delete() {
		var filecountTemp = parseInt(document.getElementById("file_cnt").value);
		if (filecountTemp > 1) {
			var parents = document.getElementById("file_add_form");
			var obj = document.getElementById("file" + filecountTemp);
			var br = document.getElementById('br' + filecountTemp);
			parents.removeChild(obj);
			parents.removeChild(br);
			document.getElementById("file_cnt").value = filecountTemp - 1;
		}
	};
	
	return {
		init : _init
	};
}());
