//숫자 두 자리 자릿수 맞추기
function select(num) {
	if (num < 10) {
		return '0' + num;
	} else {
		return num;
	}
}

// query 내부의 input 파라미터 세팅
function inpParam(query){
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
function dateFormatter(date) {
	let dateFormatt	 = new Date(date);
	
	let year		 = dateFormatt.getFullYear();
	let month		 = select(dateFormatt.getUTCMonth()+1);
	let day			 = select(dateFormatt.getUTCDate());

	let hour		 = select(dateFormatt.getHours());
	let minute		 = select(dateFormatt.getMinutes());
	let seconds		 = select(dateFormatt.getSeconds());

	let milliseconds = dateFormatt.getMilliseconds();
	
	return year+'-'+month+'-'+day+' '+hour+':'+minute+':'+seconds+'.'+milliseconds;
}


function ajaxAction(paramData){
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
			paramData.error(xhr, status, error);
		},
		beforeSend	: paramData.beforeSend,
		complete	: paramData.complete
	});
}




