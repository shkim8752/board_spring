<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회사위치</title>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=f2aihev7t7"></script>
<script type="text/javascript">
	$(function(){
		var address = $('#txtAddress').val();
		alert(address);
		
		$.ajax({
			type:'get',
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			url:'${path}/map/geocodingFind?address=' + address,
			dataType:'json',
			success : function(result){
				alert('성공');
				console.log(result);
				$('#divResult').html(result.x);
				$('#divResult').append('<br>');
				$('#divResult').append(result.y);
				mapDraw(result.x,result.y); //지도그리기 함수호출
			},
			error:function(result){
				alert('실패');
				console.log(result);
			}
		});
		
		//지도불러오기
		function mapDraw(x,y) {
			var mapOptions = {
				    center: new naver.maps.LatLng(y, x),
				    zoom: 15
				};

			//옵션을 이용한 맵만들기
			var map = new naver.maps.Map('map', mapOptions);
			
			//마커 만들기
			var marker = new naver.maps.Marker({
			    position: new naver.maps.LatLng(y, x),
			    map: map
			});
			
		}
		
	});
</script>
</head>
<body>
	회사주소 : <input id="txtAddress"  type="text" value="서울특별시 관악구 신림로 340, 르네상스복합쇼핑몰 6층" size="50"> 
	<div id="map" style="width:100%;height:400px;"></div>
</body>
</html>