<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>
	<table>
		<tr>
			<td>id</td>
			<td>商品名称</td>
			<td>商品价格</td>
			<td>已售百分比</td>
		</tr>
		<c:forEach items="${info.value }" var="goods">
			<tr>
				<td>${goods.id }</td>
				<td>${goods.name }</td>
				<td>${goods.price }</td>
				<td>${goods.bfb }</td>
			</tr>
		</c:forEach>
		<tr>
			<td><jsp:include page="/WEB-INF/view/pages.jsp"></jsp:include></td>
		</tr>
	</table>
</body>
</html>