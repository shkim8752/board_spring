<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>  
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세조회</title>
<!-- 핸들바 라이브러리 추가 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.6/handlebars.js"></script>
<!-- 핸들바 탬플릿 작성 -->
<script id="replyTemplate" type="text/x-handlebars-template">
	{{#each .}}
		<li>
			<div>
				번호 : {{rnum}} <br>
				작성자 : {{writer}} <br>
				내용 : <span id='rnum{{rnum}}' > {{content}} </span> <br>
				등록일자 : {{regdate}} <br>
				<button class ='btnReplyModify' value='{{rnum}}'>수정</button>
				<button class ='btnReplySave' value='{{rnum}}' hidden>저장</button>
				<button class ='btnReplyCancel' value='{{rnum}}' hidden>취소</button>
				<button class ='btnReplyDelete' value='{{rnum}}'>삭제</button>
			</div>
		</li>
	{{/each}}
</script>

<script type="text/javascript">
 	$(function() {
 		
 		replyList();
 		
 		//수정버튼을 클릭했을때 수정폼으로 이동
 		$('#btnModify').on('click', function(e){
 			e.preventDefault(); //객체의 기본기능 소멸
 			var bnum = $('#bnum').val();
			$(location).attr('href', '${path}/board/modify?bnum='+ bnum);
 		} );

 		//목록버튼을 클릭했을때
 		$('#btnList').on('click', function(e) {
 			e.preventDefault(); //객체의 기본기능 소멸
			$(location).attr('href', '${path}/board/list');
		});
 		
 		//삭제버튼을 눌렀을때
 		$('#btnDelete').on('click', function(e) {
 			e.preventDefault(); //객체의 기본기능 소멸
 			var result = confirm('삭제하시겠습니까?');
 			if (result){
 	 			var bnum = $('#bnum').val();
 				$(location).attr('href', '${path}/board/delete?bnum=' + bnum);			
 			}
 			
		});
		
		//다운로드 버튼을 클릭했을때
		$('.fileDownload').on('click', function(e) {
 			e.preventDefault(); //객체의 기본기능 소멸
			var filename = $(this).val();
			$(location).attr('href', '${path}/board/filedownload?filename=' +filename);
			
		});
		
		//댓글추가저장버튼
		$('#btnReplyAdd').on('click',function(){
			var bnum = $('#bnum').val(); //게시물번호
			var replyWriter = $('#replyWriter').val(); //댓글 작성자
			var replyContent = $('#replyContent').val(); //댓글 내용
			//alert(replyContent);
			//공백체크
			if(replyWriter == ''){
				alert('작성자를 입력해주세요.');
				$('#replyWriter').focus();
				return	//함수 실행 즉각 종료
			}else if(replyContent == ''){
				alert('내용을 입력해 주세요');
				$('#replyContent').focus();
				return
			}
			$.ajax({
				type:'post',
				contentType:'application/json',
				url : '${path}/reply/',
				data : JSON.stringify({bnum:bnum,writer:replyWriter,content:replyContent}), //json문자열 표기법으로 변환
				dataType : 'text',  //결과값의 타입
				success : function(result){
					alert(result);
					replyList();
					//추가데이터 클리어
					$('#replyWriter').val('');
					$('#replyContent').val('');
					
				},
				error:function(result){
					alert("error");
					console.log(result);
				}
			});
		});
		
		//댓글 조회
		$('#btnReplyList').on('click', function() {
			replyList();
		});
		
		function replyList(){
			var bnum = $('#bnum').val(); //게시물번호
			//alert(bnum);
			$.ajax({
				type:"get",
				url:'${path}/reply/' + bnum, //restfull하게 설계
				dataType : 'json', //결과값의 형태
				success:function(result){
					//alert('성공');
					console.log(result);
					replyDisplay(result);
				},
				error:function(result){
					alert('error');
				}
			});
			
			
		}
		//댓글리스트 만들고 출력
		function replyDisplay(data) {
			//핸들바 탬플릿을 이용한 리스트 만들기 
			var source = $('#replyTemplate').html();
			var template = Handlebars.compile(source);
			$('#replies').html(template(data));
		}
		
		//댓글 수정
		$('#replies').on('click', '.btnReplyModify',function(){
			var rnum = $(this).val();
			var html = '<textarea id="rnum'+ rnum + '">'+ $('#rnum'+rnum).html() +'</textarea>'; //수정할수 있게 변경
			$('#rnum'+rnum).replaceWith(html); //기존의 html을 바꾼다
			//버튼 컨트롤
			$('.btnReplySave[value=' + rnum + ']').show(); //저장버튼 보이기
			$('.btnReplyCancel[value=' + rnum + ']').show(); //취소버튼 보이기
			$('.btnReplyModify[value=' + rnum + ']').hide(); //수정버튼 감추기
			
		});
		
		//댓글 취소
		$('#replies').on('click', '.btnReplyCancel',function(){
			var rnum = $(this).val();
			var html = '<span id="rnum'+ rnum + '">'+ $('#rnum'+rnum).html() +'</span>'; //수정할수 있게 변경
			$('#rnum'+rnum).replaceWith(html); //기존의 html을 바꾼다
			//버튼 컨트롤
			$('.btnReplySave[value=' + rnum + ']').hide(); //저장버튼 감추기
			$('.btnReplyCancel[value=' + rnum + ']').hide(); //취소버튼 감추기
			$('.btnReplyModify[value=' + rnum + ']').show(); //수정버튼 보이기
			
		});
		
		//댓글수정저장버튼
		$('#replies').on('click', '.btnReplySave',function(){
			var rnum = $(this).val(); //댓글번호
			var replyContent = $('#rnum' +rnum).val(); //댓글 내용
			alert(replyContent);
			$.ajax({
				type:'put',	//수정 
				contentType:'application/json',
				url : '${path}/reply/' + rnum,
				data : JSON.stringify({content:replyContent}), //json문자열 표기법으로 변환
				dataType : 'text',  //결과값의 타입
				success : function(result){
					alert(result);				
				},
				error:function(result){
					alert("error");
					console.log(result);
				}
			});
		});
		
		
		//댓글 삭제
		$('#replies').on('click', '.btnReplyDelete',function(){
			var rnum = $(this).val();
			var bnum = $('#bnum').val();
			$.ajax({
				type:"delete",	//url에다만 보낼수 있음 
				url:'${path}/reply/' + rnum + "?bnum=" + bnum, //restfull하게 설계
				dataType : 'text', //결과값의 형태
				//delete 메소드는  바디에 data 전송 불가
				//server.xml에 pareseBodyMethods추가시 기능 
				//data: "bnum=" +bnum
				success:function(result){
					alert(result);
					replyList();
				},
				error:function(result){
					alert('error');
				}
			});
			
		});		
		
	});
 </script>

</head>
<body>
	<h2>상세조회</h2>
	<table border = "1">
		<tr>
			<td>번호</td>
			<td><input id="bnum" type="text" name="bnum" size='5' value="${board.bnum}"></td>
			<td>조회수</td>
			<td>${board.readcnt}
		</tr>
		<tr>
			<td>작성자</td>
			<td colspan="3">${board.writer}</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td colspan="3">${board.email}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td colspan="3">${board.subject}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td colspan="3"><pre>${board.content}</pre></td>
		</tr>
		<tr>
			<td>파일</td>
			<td colspan="3">
				<c:forEach var="file" items="${flist}">
					${file.filename} 
					<button class="fileDownload" value="${file.filename}">다운로드</button> <br>
				</c:forEach>
			</td>	
		</tr>
		<tr>
			<td>등록일자</td>
			<td colspan="3"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${board.regdate}"/> </td>
		</tr>
		<tr>
			<td>수정일자</td>
			<td colspan="3"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${board.updatedate}"/></td>
		</tr>
		
		<tr>
			<td colspan="4">
				<button id="btnModify">수정</button>
				<button id="btnDelete">삭제</button>
				<button id="btnList">목록</button>
			</td>
			
		</tr>
	</table>
	
	<!-- 댓글달기 -->
	<h2>댓글</h2>
	<div>
		<lable>작성자</lable>
		<input type="text" id="replyWriter" ><br>
		<label>내용</label>
		<textarea id = "replyContent" rows="2" cols="20"></textarea>
		<button id="btnReplyAdd">댓글추가</button>	
	</div>
	<!-- 댓글목록 -->
	<button id="btnReplyList">댓글조회</button>
	<div id="replies">
	
	</div>
	
	
	
	
	
	
	
	
	
	
</body>
</html>