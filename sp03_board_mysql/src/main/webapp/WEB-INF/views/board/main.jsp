<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">

  <script>
    $(document).ready(function(){
        $('.slider').bxSlider({
            auto: true,
            autoControls: true,
            stopAutoOnClick: true,
            pager: true,
            slideWidth: 600
        });
//      $('.slider').bxSlider();
    });
  </script>
  

<script type="text/javascript">
	$(function() {
		$('#boardList').on('click', function() {
			$(location).attr('href', '${path}/board/list');
		});
	});
	
	
	$(function){
		
	}
	
</script> 
	
</head>
<body>
	<header>
		<button id="boardList">게시판목록</button>
		<button>찾아오시는 길</button>	
	</header>
	<section>
	
	</section>
	<div>
		<!-- servlet-context.xml 에 이미지 경로 매핑 -->
		<img alt="메인이미지" src="${path}/resources/images/cat.jpg" width="100px">
		<img alt="메인이미지" src="${path}/img/cat.jpg" width="100px">
		<img alt="메인이미지" src="${path}/localimg/cat.jpg" width="100px">			<!-- servlect context에 있음  -->
		<!-- servet.xml에 이미지 경로 매핑 -->
		<img alt="메인이미지" src="/images/cat.jpg" width="100px">
	</div>
	
 <div class="slider">
    <div><img src="image/mount2.jpg"  width="500px" height="250px"></div>
    <div><img src="image/mountains.jpg"  width="500px" height="250px" ></div>
    <div><img src="image/sunset.jpg"  width="500px" height="250px"></div>
  </div>
	
	
	
	
	
</body>
</html>