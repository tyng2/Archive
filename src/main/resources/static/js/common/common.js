var CMJS = (function(){
	
	var _nvl = function(st, d){
		return (st) ? st : (d) ? d : '';
	};
	
	//숫자 두 자리 자릿수 맞추기
	var _select = function(num) {
		num = (num && num >= 0) ? num : 0;
		if (num < 10) {
			return '0' + num;
		} else if (num > 99) {
			let strN = num + '';
			return strN.substr(strN.length - 2);
		} else {
			return num;
		}
	};
	
	// query 내부의 input 파라미터 세팅
	var _inpParam = function(query){
		let param		= {};
		
		$(query).find('input').each(function() {
			$this		= $(this);
			name 		= $this.attr('name');
			value		= $this.val();
			if (value == '') {
				param = 1;
				Swal.fire({
					text	: name + ' 항목을 입력하세요.',
					icon	: 'info'
				});
//				alert(name + ' 항목을 입력하세요.');
				return false;
			}
			param[name]	= value;
		});
		console.log('param::'+param);
		return param;
	};
	
	//TimeStamp -> Date formatter (yyyy-mm-dd hh:mm:ss)
	var _dateFormatter = function(date) {
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
	};
	
	var _timestampFormatter = function(date){
		let d = new Date(date);
		return _dateFormatter(d);
	};
	
	
	var _ajax = function(paramData){
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
	};
	
	var _submit = function(url, param, method){
		$('#submitForm').remove();
		
		let $form	= $('<form>').attr('method', (method) ? method : 'GET');
		let $inp	= $('<input>').attr('type','hidden');

		$form.attr({'action':url,'id':'submitForm'});
		$.each(param, function(k, v){
			$form.append($inp.clone().attr('name',k).val(v));
		});
		$('body').append($form);
		$form.submit();
	};
	
	var _getQueryString = function(param){
		let str = '?';
		let ind = 0;
		$.each(param, function(k, v){
			if (ind++ != 0) {
				str += '&';
			}
			str += k;
			str += '=';
			str += v;
		});
		return str;
	};
	
	return {
		nvl					: _nvl,
		inpParam			: _inpParam,
		dateFormatter		: _dateFormatter,
		timestampFormatter	: _timestampFormatter,
		ajax				: _ajax,
		submit				: _submit,
		getQueryString		: _getQueryString
	};
}());
