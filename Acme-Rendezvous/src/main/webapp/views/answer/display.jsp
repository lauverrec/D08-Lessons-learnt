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


<display:table name="Question" class="displaytag"
  requestURI="${requestURI}" id="row">
  	
  	
	<!-- Attributes -->

	
		<spring:message code="question.name" var="nameHeader" />
		<display:column property="name" title="${nameHeader}" sortable="true"/>
		
		
		
		<%-- <!-- Display -->
	<jstl:if test="${!(row.trip == null)}">
	<spring:message code="question.trip" var="tripHeader" />
	<display:column title="${tripHeader}" class="<%= estilo %>">
		<spring:url value="trip/display.do" var="displayURL">
		<spring:param name="tripId" value="${row.trip.id}"/>
		</spring:url>
		<a href="${displayURL}"><spring:message code="question.trip"/></a>
	</display:column>
 	</jstl:if> --%>
</display:table>