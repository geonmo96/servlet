# servlet

1. http://servlets.com/
2. banner 3rd upload.;
3. com.oreilly.servlet
4. cos-20.08.zip 다운
5. 압축 풀고 나오는 cos.jar파일을 lib(project/webContent/WEB_INF/lib)에 복사


<form> 태그의 enctype 속성은 폼 데이터(form data)가 서버로 제출될 때 해당 데이터가 인코딩되는 방법을 명시합니다.
이 속성은 <form> 요소의 method 속성값이 “post”인 경우에만 사용할 수 있습니다.
multipart/form-data
	모든 문자를 인코딩하지 않음을 명시함.
	이 방식은 <form> 요소가 파일이나 이미지를 서버로 전송할 때 주로 사용함.	
        
// application.getrealpath(path) : path 폴더의 실질적인 경로를 반환
// enctype="multipart/form-data" 으로 인코딩타입을 정한 경우 일반적(request.get..)으로 전달받을 수 없음
// new DefaultFileRenamePolicy() > 중복된 이름의 경우 numbering이 부여됨

- FileDownload.java 노이해
