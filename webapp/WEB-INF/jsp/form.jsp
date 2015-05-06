<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="./commons/_head.jspf" %>

</head>
<body>
    <%@ include file="./commons/_top.jspf" %>

	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography">
				<div class="page-header">
					<c:set var="pageName" value="회원가입" />
					<c:if test="${isUpdate}">
					<c:set var="pageName" value="개인정보수정" />
					</c:if>
					<h1>${pageName}</h1>
				</div>
				
				<c:set var="actionUrl" value="/users/create" />
				<c:if test="${isUpdate}">
				<c:set var="actionUrl" value="/users/update" />
				</c:if>
				
				<form:form modelAttribute="user" cssClass="form-horizontal" action="${actionUrl}" method="post">
					<c:if test="${not empty errorMessage}">
					<div class="control-group">
						<label class="error">${errorMessage}</label>
					</div>
					</c:if>
					<div class="control-group">
						<label class="control-label" for="userId">사용자 아이디</label>
						<div class="controls">
							<c:choose>
							<c:when test="${isUpdate}">
							<form:input path="userId" readonly="true"/>
							<%-- <input type="hidden" name="userId" value="${user.userId}" /> --%>
							</c:when>
							<c:otherwise>
							<form:input path="userId"/>
							<%-- <input type="text" name="userId" value="${user.userId}" /> --%>
							</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">비밀번호</label>
						<div class="controls">
							<form:password path="password" />
							<%-- <input type="password" id="password" name="password" value="${user.password}" placeholder=""> --%>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="name">이름</label>
						<div class="controls">
							<form:input path="name" />
							<%-- <input type="text" id="name" name="name" value="${user.name}" placeholder=""> --%>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="email">이메일</label>
						<div class="controls">
							<form:input path="email" />
							<%-- <input type="text" id="email" name="email" value="${user.email}" placeholder=""> --%>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-primary">${pageName}</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>