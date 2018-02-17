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
	name="rendezvous" requestURI="${requestURI }" id="row">

	<security:authorize access="hasRole('USER')">
		<spring:message code="rendezvous.edit" var="Edit" />

		<display:column title="${Edit}" sortable="true">
			<jstl:if test="${row.draftMode==true }">
				<spring:url value="rendezvous/user/edit.do" var="editURL">
					<spring:param name="rendezvouseId" value="${row.id}" />
				</spring:url>
				<a href="${editURL}"><spring:message code="rendezvous.edit" /></a>
			</jstl:if>
		</display:column>
	</security:authorize>

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

	<spring:message code="rendezvous.announcement" var="announcements" />
	<display:column title="${announcements}" sortable="true">
		<spring:url value="announcement/user/list.do" var="announcementURL">
			<spring:param name="rendezvousId" value="${row.id }" />
		</spring:url>
		<a href="${announcementURL}"><spring:message
				code="rendezvous.announcement" /></a>
	</display:column>

	<security:authorize access="hasRole('USER')">
		<spring:message code="rendezvous.createAnnouncement"
			var="createAnnouncement" />
		<display:column title="${createAnnouncement}" sortable="true">
			<spring:url value="announcement/user/create.do" var="editURL">
				<spring:param name="rendezvousId" value="${row.id}" />
			</spring:url>
			<a href="${editURL}"><spring:message
					code="rendezvous.createAnnouncement1" /></a>
		</display:column>
	</security:authorize>
	<spring:message code="rendezvouse.question" var="Question" />
	<display:column title="${Question}" sortable="true">
		<spring:url value="question/list.do" var="editURL">
			<spring:param name="rendezvouseId" value="${row.id}" />
		</spring:url>
		<a href="${editURL}"><spring:message code="rendezvouse.question" /></a>
	</display:column>

	<!-- Boton de delete para el administrador ya que puede borrar las Rendezvous que quiera pero no editarlas -->
	<security:authorize access="hasRole('ADMINISTRATOR')">
		<spring:message code="rendezvous.delete" var="delete" />

		<display:column title="${delete}" sortable="true">
				<spring:url value="rendezvous/administrator/delete.do" var="editURL">
					<spring:param name="rendezvousId" value="${row.id}" />
				</spring:url>
				<a href="${editURL}"><spring:message code="rendezvous.delete" /></a>
		</display:column>
	</security:authorize>

</display:table>
<security:authorize access="hasRole('USER')">
	<div>
		<a href="rendezvous/user/create.do"> <spring:message
				code="rendezvous.create" />
		</a>
	</div>
</security:authorize>

