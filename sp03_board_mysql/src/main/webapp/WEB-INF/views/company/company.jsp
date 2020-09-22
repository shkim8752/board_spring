<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ����ġ</title>
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
				alert('����');
				console.log(result);
				$('#divResult').html(result.x);
				$('#divResult').append('<br>');
				$('#divResult').append(result.y);
				mapDraw(result.x,result.y); //�����׸��� �Լ�ȣ��
			},
			error:function(result){
				alert('����');
				console.log(result);
			}
		});
		
		//�����ҷ�����
		function mapDraw(x,y) {
			var mapOptions = {
				    center: new naver.maps.LatLng(y, x),
				    zoom: 15
				};

			//�ɼ��� �̿��� �ʸ����
			var map = new naver.maps.Map('map', mapOptions);
			
			//��Ŀ �����
			var marker = new naver.maps.Marker({
			    position: new naver.maps.LatLng(y, x),
			    map: map
			});
			
		}
		
	});
</script>
</head>
<body>
	ȸ���ּ� : <input id="txtAddress"  type="text" value="����Ư���� ���Ǳ� �Ÿ��� 340, ���׻󽺺��ռ��θ� 6��" size="50"> 
	<div id="map" style="width:100%;height:400px;"></div>
</body>
</html>