var common = (function(){
	
	function _nvl(st){
		return (st) ? st : '';
	}
	
	//숫자 두 자리 자릿수 맞추기
	function _select(num) {
		if (num < 10) {
			return '0' + num;
		} else {
			return num;
		}
	}
	
	// query 내부의 input 파라미터 세팅
	function _inpParam(query){
		let param		= {};
		
		$(query).find('input').each(function() {
			$this		= $(this);
			name 		= $this.attr('name');
			value		= $this.val();
			if (value == '') {
				param = 1;
				alert(name + ' 항목을 입력하세요.');
				return false;
			}
			param[name]	= value;
		});
		console.log('param::'+param);
		return param;
	}
	
	//TimeStamp -> Date formatter (yyyy-mm-dd hh:mm:ss.s)
	//TimeStamp -> Date formatter (yyyy-mm-dd hh:mm:ss)
	function _dateFormatter(date) {
		let dateFormatt	 = new Date(date);
		
		let year		 = dateFormatt.getFullYear();
		let month		 = _select(dateFormatt.getUTCMonth()+1);
		let day			 = _select(dateFormatt.getUTCDate());
	
		let hour		 = _select(dateFormatt.getHours());
		let minute		 = _select(dateFormatt.getMinutes());
		let seconds		 = _select(dateFormatt.getSeconds());
	
	//	let milliseconds = dateFormatt.getMilliseconds();
		
	//	return year+'-'+month+'-'+day+' '+hour+':'+minute+':'+seconds+'.'+milliseconds;
		return year+'-'+month+'-'+day+' '+hour+':'+minute+':'+seconds;
	}
	
	
	function _ajax(paramData){
		$.ajax({
			url			: paramData.url,
			type		: paramData.type,
			header		: {'x-kframe-ajax-call' : 'Y'},
			dataType	: paramData.dataType,
			data		: paramData.data,
			success		: function(res) {
				paramData.success(res);
			},
			error		: function(xhr, status, error) {
				if ($.type(paramData.error) == 'function') {
					paramData.error(xhr, status, error);
				} else {
					console.log(xhr, status, error);
				}
			},
			beforeSend	: paramData.beforeSend,
			complete	: paramData.complete
		});
	}
	
	return {
		nvl				: _nvl,
		inpParam		: _inpParam,
		dateFormatter	: _dateFormatter,
		ajax			: _ajax
	};
}());
