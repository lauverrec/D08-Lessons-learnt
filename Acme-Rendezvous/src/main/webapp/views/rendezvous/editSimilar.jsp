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



<form:form action="rendezvous/user/editSimilar.do"
	modelAttribute="rendezvouse">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="assistants" />
	<form:hidden path="announcements" />
	<form:hidden path="name" />
	<form:hidden path="description" />
	<form:hidden path="picture" />
	<form:hidden path="organisedMoment" />
	<form:hidden path="draftMode" />
	<form:hidden path="forAdult" />
	<form:hidden path="gps.longitude" />
	<form:hidden path="gps.latitude" />




	<form:label path="similarRendezvouses">
		<spring:message code="rendezvous.similarRendezvouses" />:
	</form:label>
	<form:select id="similarRendezvouses" path="similarRendezvouses">
		<form:options items="${similarRendezvouses}" itemValue="id"
			itemLabel="name" />
	</form:select>
	<form:errors cssClass="error" path="similarRendezvouses" />
	<br />
	<br />

	<!-- botones -->

	<input type="submit" name="link"
		value="<spring:message code="rendezvous.link"/>" />&nbsp;
		
	<input type="submit" name="unlink"
	value="<spring:message code="rendezvous.unlink"/>" />&nbsp;

	<input type="button" name="cancel"
		value="<spring:message code="rendezvous.cancel"/>"
		onclick="javascript: window.location.replace('rendezvous/user/list.do')" />
	<br />

</form:form>


