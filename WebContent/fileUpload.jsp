<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fileUpload.jsp</title>
</head>
<body>
	<% String path = application.getRealPath("fileUpDown"); %>
	<c:set var="realPath" value="<%=path%>"></c:set>
	<c:set var="req" value="<%=request %>"></c:set>
	폴더 경로 : ${realPath }<br>
	<jsp:useBean id="file" class="file.FileUpload"></jsp:useBean>
	<c:set var="files" value="${file.fileUpload(realPath, req) }"></c:set>
	
	<hr>
	저장될 파일 이름 : ${files[0] }<br>
	실제 파일 이름 : ${files[1] }<br>
	
</body>
</html>