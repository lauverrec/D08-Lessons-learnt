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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<form:form action="announcement/administrator/delete.do" modelAttribute="announcement">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="rendezvouse" />
	<form:hidden path="madeMoment" />





	<form:label path="title">
		<spring:message code="announcement.title" />:
	</form:label>
	<form:input path="title" readonly="true" />
	<form:errors cssClass="error" path="title" />
	<br />
	<br />

	<form:label path="description">
		<spring:message code="announcement.description" />:
		<br />
	</form:label>
	<form:textarea readonly="true" rows="5px" cols="24px" path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	<br />

	
	<!-- botones -->

	<input type="submit" name="delete"
			value="<spring:message code="announcement.delete" />"
			onclick="javascript: return confirm('<spring:message code="announcement.confirm.delete" />')" />&nbsp;

	<input type="button" name="cancel"
		value="<spring:message code="announcement.back" />"
		onclick="javascript:  window.location.replace('announcement/user/list.do');" />
	<br /> 

</form:form>