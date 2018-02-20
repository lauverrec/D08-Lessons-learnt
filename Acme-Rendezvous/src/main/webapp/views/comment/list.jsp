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
	name="comments" requestURI="${requestURI}" id="row">

	<security:authorize access="hasRole('ADMINISTRATOR')">
		<spring:message code="comment.delete" var="delete" />

		<display:column title="${deleteURL}" sortable="true">
			<spring:url value="comment/administrator/delete.do" var="deleteURL">
				<spring:param name="commentId" value="${row.id}" />
			</spring:url>
			<a href="${deleteURL}"><spring:message code="comment.delete" /></a>
		</display:column>
	</security:authorize>

	<!-- ATRIBUTOS -->

	<spring:message code="comment.format.writtenMoment" var="pattern"></spring:message>
	<spring:message code="comment.writtenMoment" var="momentHeader" />
	<display:column property="writtenMoment" title="${momentHeader}"
		sortable="true" format="${pattern}" />

	<spring:message code="comment.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" sortable="true" />


	<spring:message code="comment.picture" var="pictureHeader" />
	<display:column title="${pictureHeader}">
	<div
		style="position: relative; width: 500px; height: 300px; margin-left:
		auto; margin-right: auto;"> <img src="${row.picture}" width="500"
			height="300">
	</div>
	</display:column>

	



</display:table>



