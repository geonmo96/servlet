<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fileFormOK.jsp</title>
</head>
<body>
	<%
		String path = application.getRealPath("fileUpDown");
		// application.getrealpath(path) : path 폴더의 실질적인 경로를 반환
		out.print("path : " + path);
		
		// 받아온 파일을 저장할 때의 사이즈를 설정함
		int size = 102400 * 10; // 10K
		String file = ""; // original file을 저장할 때 중복되는 이름을 방지하기 위해 numbering을 할 것임
		String oriFile = ""; 
		
		// enctype="multipart/form-data" 으로 인코딩타입을 정한 경우 일반적(request.get..)으로 전달받을 수 없음
		MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
		// new DefaultFileRenamePolicy() > 중복된 이름의 경우 numbering이 부여됨
		
		file = multi.getFilesystemName("file");
		oriFile = multi.getOriginalFileName("file");
		
		out.print("저장될 파일 이름 : " + file);
		out.print("실제 파일 이름 : " + oriFile);
		
		response.sendRedirect("fileForm.jsp");
	%>
</body>
</html>