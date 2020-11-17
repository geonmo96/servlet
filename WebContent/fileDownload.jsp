<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fileDownload.jsp</title>
</head>
<body>
	<jsp:useBean id="fileDownload" class="file.FileDownload"></jsp:useBean>
	<c:set var="req" value="<%=request %>"></c:set>
	<c:set var="res" value="<%=response %>"></c:set>
	${fileDownload.fileDown(req, res) }
</body>
</html>