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


<form:form action="user/edit.do" modelAttribute="user">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount"/>
	<form:hidden path="userAccount.authorities"/>
	<form:hidden path="rendezvousesCreated"/>
	<form:hidden path="rendezvousesAssisted"/>
	
	
	<jstl:if test="${user.id == 0}">		
		<form:label path="userAccount.username">
			<spring:message code="user.username" />:
		</form:label>
		<form:input path="userAccount.username" />
		<form:errors cssClass="error" path="userAccount.username" />
		<br /><br />
	
		<form:label path="userAccount.password">
			<spring:message code="user.password" />:
		</form:label>
		<form:password path="userAccount.password" />
		<form:errors cssClass="error" path="userAccount.password" />
		<br /><br />
	</jstl:if>
	
	<form:label path="name">
		<spring:message code="user.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br /><br />
	
	<form:label path="surname">
		<spring:message code="user.surname" />:
	</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br /><br />
	
	<form:label path="birthDate" >
		<spring:message code="user.birthDate" />:
	</form:label>
	<form:input path="birthDate" placeholder=" yyyy/MM/dd"/>
	<form:errors cssClass="error" path="birthDate" />
	<br /><br />
	
	<form:label path="postalAddress">
		<spring:message code="user.postalAddress" />:
	</form:label>
	<form:input path="postalAddress" />
	<form:errors cssClass="error" path="postalAddress" />
	<br /><br />
	
	<form:label path="phoneNumber">
		<spring:message code="user.phoneNumber" />:
	</form:label>
	<form:input path="phoneNumber" />
	<form:errors cssClass="error" path="phoneNumber" />
	<br /><br />
	
	<form:label path="emailAddress">
		<spring:message code="user.emailAddress" />:
	</form:label>
	<form:input path="emailAddress" />
	<form:errors cssClass="error" path="emailAddress" />
	<br /><br />
	
	<input type="submit" name="save"
		value="<spring:message code="user.save" />" />&nbsp; 

	<input type="button" name="cancel"
		value="<spring:message code="user.cancel" />"
		onclick="javascript: window.location.replace('welcome/index.do');" />
	<br />

</form:form>