<%--
 * list.jsp
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



<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="users" requestURI="${RequestUri}" id="row">
	
	
	
	<!-- Attributes -->

	<spring:message code="user.name" var="nameHeader" />:
	<display:column property="name" title="${nameHeader}" sortable="true" />
	
	<spring:message code="user.surname" var="surnameHeader" />:
	<display:column property="surname" title="${surnameHeader}" sortable="true" />
	
	<!-- Action links -->

		
	<spring:message code="user.profile" var="profileHeader" />
	<display:column title="${profileHeader}" sortable="true">
		
		<spring:url value="user/display.do" var="profileURL">
		<spring:param name="userId" value="${row.id}"/>
		</spring:url>
		<a href="${profileURL}"><spring:message code="user.profile"/></a>
		
	</display:column>	
	
	
</display:table>