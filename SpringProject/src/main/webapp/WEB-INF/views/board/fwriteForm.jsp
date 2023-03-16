<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/fwriteForm.jsp</title>
</head>
<body>
<%
// String id=(String)session.getAttribute("id");
// if(id==null){
// 	response.sendRedirect("MemberLoginForm.me");
//}
%>
<h1>파일첨부 글쓰기</h1>
<!-- 첨부파일 있을때 POST방식 및 enctype="multipart/form-data" 추가 -->
<form action="${pageContext.request.contextPath}/board/fwritePro" method="post" enctype="multipart/form-data">

<table border="1">
<tr><td>글쓴이</td>
    <td><input type="text" name="name" value="${sessionScope.id }" readonly></td></tr>
<tr><td>글제목</td>
    <td><input type="text" name="subject" ></td></tr>  
<tr><td>첨부파일</td>
    <td><input type="file" name="file" ></td></tr>      
<tr><td>글내용</td>
    <td><textarea name="content" rows="10" cols="20"></textarea></td></tr>
<tr><td colspan="2"><input type="submit" value="글쓰기"></td></tr>    
</table>

</form>

</body>
</html>


