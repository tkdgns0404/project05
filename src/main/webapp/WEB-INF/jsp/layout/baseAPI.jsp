<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>2팀 프로젝트</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body>
	<section class="content">
		<tiles:insertAttribute name="header"/>
		<tiles:insertAttribute name="body"/>
	</section>
</body>
<jsp:include page="../common/foot.jsp"></jsp:include>
</html>