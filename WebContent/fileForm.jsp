<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fileForm.jsp</title>
</head>
<body>
<!-- 
			<form> 태그의 enctype 속성은 폼 데이터(form data)가 서버로 제출될 때 해당 데이터가 인코딩되는 방법을 명시합니다.
			이 속성은 <form> 요소의 method 속성값이 “post”인 경우에만 사용할 수 있습니다.
			multipart/form-data
				모든 문자를 인코딩하지 않음을 명시함.
				이 방식은 <form> 요소가 파일이나 이미지를 서버로 전송할 때 주로 사용함.	
			
			
		 -->
	<form action="fileUpload.jsp" method="post" enctype="multipart/form-data">
		
		파일 : <input type="file" name="file"><br>
		<input type="submit" value="업로드">
	</form>
	<hr>
	<!-- upload list print -->
	<%--
		<%
			String saveDir = application.getRealPath("fileUpDown");
			File dir = new File(saveDir);
			String fName[] = dir.list();
			for(String s : fName){
				out.print(s + "<br>");
			}
		%>
	 --%>
	 <% String path = application.getRealPath("fileUpDown"); %>
	 <c:set var="realPath" value="<%=path %>"></c:set>
	 <c:set var="req" value="<%=request %>"></c:set>
	 <jsp:useBean id="filedown" class="file.FileDownload"></jsp:useBean>
	 <c:forEach var="fileName" items="${filedown.fileList(realPath, req) }">
	 	<a href="fileDownload.jsp?fileName=${fileName }">${fileName }</a><br>
	 </c:forEach>
</body>
</html>