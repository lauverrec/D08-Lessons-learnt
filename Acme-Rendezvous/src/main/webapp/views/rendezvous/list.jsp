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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="rendezvous" requestURI="rendezvous/user/list.do" id="row">

	<spring:message code="rendezvous.edit" var="Edit" />
	<display:column title="${Edit}" sortable="true">
		<spring:url value="rendezvous/user/edit.do" var="editURL">
			<spring:param name="rendezvouseId" value="${row.id}" />
		</spring:url>
		<a href="${editURL}"><spring:message code="rendezvous.edit" /></a>
	</display:column>

	<spring:message code="rendezvouse.name" var="titleHeader" />
	<display:column property="name" title="${titleHeader}" sortable="true" />

	<spring:message code="rendezvouse.description" var="titleHeader" />
	<display:column property="description" title="${titleHeader}"
		sortable="true" />

	<spring:message code="rendezvouse.organisedMoment" var="titleHeader" />
	<display:column property="organisedMoment" title="${titleHeader}"
		sortable="true" />

	<spring:message code="rendezvouse.picture" var="titleHeader" />
	<display:column property="picture" title="${titleHeader}"
		sortable="true" />
		



</display:table>
<security:authorize access="hasRole('USER')">


	<div>
		<a href="rendezvous/user/create.do"> <spring:message
				code="rendezvous.create" />
		</a>
	</div>
</security:authorize>

