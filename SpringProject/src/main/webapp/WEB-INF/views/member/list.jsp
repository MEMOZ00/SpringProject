<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/list.jsp</title>
</head>
<body>
<h1>member/list.jsp</h1>
<%
// // MemberDAO 객체생성
// MemberDAO dao=new MemberDAO();
// // 리턴할형 ArrayList<MemberDTO>  getMemberList() 메서드 정의 
// // ArrayList<MemberDTO>  memberList = dao.getMemberList() 메서드 호출
// ArrayList<MemberDTO> memberList =dao.getMemberList();

// ArrayList<MemberDTO> memberList=
//      (ArrayList<MemberDTO>)request.getAttribute("memberList");

%>
<table border="1">

<tr>
<td>아이디</td>
<td>비밀번호</td>
<td>이름</td>
<td>가입날짜</td>
</tr>

<c:forEach var="memberDTO" items="${memberList}">
	<tr>
	<td>${memberDTO.id}</td>
	<td>${memberDTO.pass}</td>
	<td>${memberDTO.name}</td>
	<td>${memberDTO.date}</td>
	</tr>
</c:forEach>

</table>
<%
//배열 접근 => for
// for(int i=0;i<memberList.size();i++){
	//배열 한칸에 내용 가져오기 
// 	MemberDTO dto=memberList.get(i);
	%>
<tr><td><%//=dto.getId() %></td>
    <td><%//=dto.getPass() %></td>
    <td><%//=dto.getName() %></td>
    <td><%//=dto.getDate() %></td></tr>	
	<%
// }
%>
</body>
</html>



