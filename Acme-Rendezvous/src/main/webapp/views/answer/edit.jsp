<%--
 * edit.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="${requestURI}" modelAttribute="Answer">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="user"/>
	
<!-- ATRIBUTOS -->

<form:label path="question.name">
		<spring:message code="answer.question" />:
	</form:label>
	<form:input path="question.name" readonly="true"/>
	<form:errors cssClass="error" path="question.name" />
	<br />
	<br />
	
	
	<form:label path="reply">
		<spring:message code="answer.reply" />:
	</form:label>
	<form:input path="reply" readonly="true"/>
	<form:errors cssClass="error" path="reply" />
	<br />
	<br />

<!-- BOTONES -->	
	<jstl:if test="${Answer.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="answer.delete" />"
			onclick="javascript: return confirm('<spring:message code="answer.confirm.delete" />')" />&nbsp;
	</jstl:if>
	
	<input type="button" name="cancel"
		value="<spring:message code="answer.cancel" />"
		onclick="javascript:  window.location.replace('answer/user/list.do');" />
	<br />
</form:form>
