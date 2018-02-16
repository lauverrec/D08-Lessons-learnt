<%--
 * edit.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>




<form:form action="administrator/edit.do" modelAttribute="administrator">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount"/>
	<form:hidden path="userAccount.authorities"/>


	
	<security:authorize access="hasRole('ADMINISTRATOR')">
	
	<jstl:if test="${administrator.id == 0}">		
		<form:label path="userAccount.username">
			<spring:message code="administrator.username" />:
		</form:label>
		<form:input path="userAccount.username" />
		<form:errors cssClass="error" path="userAccount.username" />
		<br /><br />
	
		<form:label path="userAccount.password">
			<spring:message code="administrator.password" />:
		</form:label>
		<form:password path="userAccount.password" />
		<form:errors cssClass="error" path="userAccount.password" />
		<br /><br />
	</jstl:if>
	
	<form:label path="name">
		<spring:message code="administrator.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	
	<form:label path="surname">
		<spring:message code="administrator.surname" />:
	</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />
	
	<form:label path="emailAddress">
		<spring:message code="administrator.emailAddress" />:
	</form:label>
	<form:input path="emailAddress" />
	<form:errors cssClass="error" path="emailAddress" />
	<br />
	
	<form:label path="postalAddress">
		<spring:message code="administrator.postalAddress" />:
	</form:label>
	<form:input path="postalAddress" />
	<form:errors cssClass="error" path="postalAddress" />
	<br />
	
	<form:label path="phoneNumber">
		<spring:message code="administrator.phoneNumber" />:
	</form:label>
	<form:input path="phoneNumber"/>
	<form:errors cssClass="error" path="phoneNumber"/>
	<br/>
	<form:errors cssClass="error" path="phoneNumber" />
	<br />
	
	
	<input type="submit" name="save"
		value="<spring:message code="administrator.save" />" />&nbsp; 
	
	<input type="button" name="cancel"
		value="<spring:message code="administrator.cancel" />"
		onclick="javascript: window.location.replace('welcome/index.do');" />
	<br />
	
	</security:authorize>
	
</form:form>