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
	name="announcements" requestURI="${requestURI}" id="row">

<jstl:if test="${editar}">
	<security:authorize access="hasRole('USER')">
	<spring:message code="announcement.edit" var="Edit" />
	
	<display:column title="${Edit}" sortable="true">
		<spring:url value="announcement/user/edit.do" var="editURL">
			<spring:param name="announcementId" value="${row.id}" />
		</spring:url>
		<a href="${editURL}"><spring:message code="announcement.edit" /></a>
	</display:column>
	</security:authorize>
</jstl:if>
	
	<spring:message code="announcement.format.madeMoment" var="pattern"></spring:message>
	<spring:message code="announcement.madeMoment" var="momentHeader2" />
	<display:column property="madeMoment" title="${momentHeader2}" sortable="true" format="${pattern}" />
	
	<spring:message code="announcement.title" var="titleHeader1" />
	<display:column property="title" title="${titleHeader1}" sortable="true" />
		
	
	<spring:message code="announcement.description" var="titleHeader3" />
	<display:column property="description" title="${titleHeader3}" sortable="true" />
	
		<!-- Boton de delete para el administrador ya que puede borrar las Announcement que quiera pero no editarlas -->
	<security:authorize access="hasRole('ADMINISTRATOR')">
		<spring:message code="announcement.delete" var="delete" />

		<display:column title="${delete}" sortable="true">
				<spring:url value="announcement/administrator/delete.do" var="editURL">
					<spring:param name="announcementId" value="${row.id}" />
				</spring:url>
				<a href="${editURL}"><spring:message code="announcement.delete" /></a>
		</display:column>
	</security:authorize>
		
</display:table>


<jstl:if test="${boton}">
<security:authorize access="hasRole('USER')">
<br />
<input type="button" name="cancel"
		value="<spring:message code="announcement.back" />"
		onclick="javascript: window.location.replace('rendezvous/user/list.do');" />
</security:authorize>
</jstl:if>



<security:authorize access="isAnonymous()">
<br />


<input type="button" name="back"
		value="<spring:message code="rendezvous.back" />"
		onclick="javascript: window.location.replace('rendezvous_/list-unregister.do');" />
</security:authorize>
