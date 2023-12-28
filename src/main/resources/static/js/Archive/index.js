/**
 * 
 */

$(function() {
	function listSite(){
		ajaxAction({
			url: 'siteList.do',
			success: function(result) {
				
				var $siteList	= $('#siteList').empty();
				var $h2 		= $('<h2>');
				var $a			= $('<a>');
				var $inp		= $('<input>').attr('type','hidden');
				var $img		= $('<img>').attr('src','images/close.png').attr('width','18px;');
				var $h2Clone;
				
				for (var i in result) {
					$h2Clone = $h2.clone().appendTo($siteList);
					$a.clone().attr('href',result[i].url).attr('target','_blank').attr('style','margin-right:2rem;').html(result[i].siteName).appendTo($h2Clone);
					$inp.clone().attr('id','siteNum'+i).val(result[i].siteNum).appendTo($h2Clone);
					$a.clone().attr('style','cursor: pointer;').attr('onclick','delSite('+i+')').html($img.clone()).appendTo($h2Clone);
				}
			},
			error: function(xhr, status, error){
				console.log(xhr, status, error);
			}
		});
	}
	
	function delSite(i){
		var id = '#siteNum' + i;
		var siteNum = $(id).val();
		console.log(siteNum);
		var param = {
			'siteNum': siteNum	
		};
		ajaxAction({
			url		: 'siteDelete.do',
			data	: param,
			success	: function() {
				listSite();
			}
		});
		return false;
	}
	
	
	var typed = new Typed('.typed-words', {
		strings 	: [ 'C', ' Java', ' Python', ' SQL', ' Web' ],
		typeSpeed 	: 80,
		backSpeed 	: 80,
		backDelay 	: 4000,
		startDelay	: 1000,
		loop 		: true,
		showCursor 	: true
	});
	
	
	listSite();
	
	$('#google').click(function() {
		$('#searchNAVER').attr('style', 'display:none;');
		$('#searchGoogle').removeAttr('style');
	});
	
	$('#naver').click(function() {
		$('#searchGoogle').attr('style', 'display:none;');
		$('#searchNAVER').removeAttr('style');
	});
	
	$('#addSite').click(function() {
		$addSite	= $('#addSite').attr('disabled', 'disabled');
		
		var name	= $('#name').val();
		var url		= $('#url').val();
		
		var param = {
			'name'	: name,
			'url'	: url
		};
		ajaxAction({
			url		: 'siteProcess.do',
			type	: 'post',
			data	: param,
			success	: function() {
				listSite();
				var name	= document.querySelector('#name');
				name.value	= '';
				var url		= document.querySelector('#url');
				url.value	= '';
			}
		});
		$addSite = $('#addSite').removeAttr('disabled');
	});
});
