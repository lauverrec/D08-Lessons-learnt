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


<display:table name="user" class="displaytag"
  requestURI="${RequestUri}" id="row">
  
  <!-- Attributes -->

	<display:column>
	<spring:message code="user.name" />:
	<jstl:out value="${row.name}"></jstl:out>
	

	<p>
		<spring:message code="user.surname" />:
		<jstl:out value="${row.surname}"></jstl:out>
	</p>
	
	<p>
		<spring:message code="user.postalAddress" />:
		<jstl:out value="${row.postalAddress}"></jstl:out>
	</p>

	<p>
		<spring:message code="user.phoneNumber" />:
		<jstl:out value="${row.phoneNumber}"></jstl:out>
	</p>
	
	<p>
		<spring:message code="user.emailAddress" />:
		<jstl:out value="${row.emailAddress}"></jstl:out>
	</p>
	
	<p>
		<spring:message code="user.rendezvouse.name"></spring:message>:
		<jstl:forEach var="rendezvouse" items="${row.rendezvousesAssisted}">
			<jstl:out value="${rendezvouse.name}"></jstl:out><br/>
		</jstl:forEach>	
	</p>
	
	
		<spring:url value="rendezvous_/list.do" var="renURL">
		<spring:param name="userId" value="${row.id}"/>
		</spring:url>
		<a href="${renURL}"><spring:message code="user.rendezvouse"/></a>
		


</display:column>
  
</display:table>