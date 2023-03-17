<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/fupdateForm.jsp</title>
</head>
<body>
<%
// //http://localhost:8080/webProject/board/updateForm.jsp?num=2
// //request 에 저장된 num 파라미터값 가져오기
// int num=Integer.parseInt(request.getParameter("num"));
// //BoardDAO 객체생성
// BoardDAO dao=new BoardDAO();
// //BoardDTO  dto = dao.getBoard(num) 메서드 호출
// BoardDTO dto=dao.getBoard(num);

// BoardDTO dto=(BoardDTO)request.getAttribute("dto");
%>
<h1>파일 글수정</h1>
<form action="${pageContext.request.contextPath }/board/fupdatePro" method="post" enctype="multipart/form-data">
<input type="hidden" name="num" value="${boardDTO.num }">
<table border="1">
<tr><td>글쓴이</td>
    <td><input type="text" name="name" value="${boardDTO.name }" readonly></td></tr>
<tr><td>글제목</td>
    <td><input type="text" name="subject" value="${boardDTO.subject }"></td></tr>  
<tr><td>첨부파일</td>
    <td><input type="file" name="file" >${boardDTO.file }
    <input type="hidden" name="oldfile" value="${boardDTO.file }">
    </td></tr>   
<tr><td>글내용</td>
    <td><textarea name="content" rows="10" cols="20">${boardDTO.content }</textarea></td></tr>
<tr><td colspan="2"><input type="submit" value="글수정"></td></tr>    
</table>
</form>
</body>
</html>