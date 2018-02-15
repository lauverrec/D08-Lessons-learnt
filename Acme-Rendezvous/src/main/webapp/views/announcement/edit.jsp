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



<form:form action="xxx/manager/edit.do" modelAttribute="XXX">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="manager" />





	<form:label path="code">
		<spring:message code="xxx.code" />:
	</form:label>
	<form:input path="code" readonly="true" />
	<form:errors cssClass="error" path="code" />
	<br />
	<br />

	<form:label path="text">
		<spring:message code="xxx.text" />:
	</form:label>
	<form:input path="text" />
	<form:errors cssClass="error" path="text" />
	<br />
	<br />

	<form:label path="gauge">
		<spring:message code="xxx.gauge" />:
	</form:label>

	<form:input path="gauge" placeholder="1 or 2 or 3" />
	<form:errors cssClass="error" path="gauge" />
	<br />
	<br />

	<form:label path="createMoment">
		<spring:message code="xxx.createMoment" />:
	</form:label>
	<form:input path="createMoment" placeholder="yyyy/MM/dd"
		readonly="true" />
	<form:errors cssClass="error" path="createMoment" />
	<br />
	<br />

	<form:label path="displayedMoment">
		<spring:message code="xxx.displayedMoment" />:
	</form:label>
	<form:input path="displayedMoment" placeholder="yyyy/MM/dd HH:mm" />
	<form:errors cssClass="error" path="displayedMoment" />
	<br />
	<br />


	<jstl:if test="${(row.trip == null)}">
		<h2>
			<spring:message code="xxx.trip.display" />
		</h2>
		<form:label path="trip">
			<spring:message code="xxx.trip.display" />:
 </form:label>
		<form:select id="trip" path="trip">
			<form:option value="0" label="----" />
			<form:options items="${trips}" itemValue="id" itemLabel="title" />
		</form:select>
		<form:errors cssClass="error" path="trip" />
		<br />
		<br />
	</jstl:if>
	<!-- botones -->

	<input type="submit" name="save"
		value="<spring:message code="xxx.save"/>" />&nbsp;
		
	<jstl:if test="${XXX.id !=0 }">
		<input type="submit" name="delete"
			value="<spring:message code="xxx.delete"/>"
			onclick="javascript: return confirm('<spring:message code="xxx.confirm.delete"/>')" />&nbsp;
	</jstl:if>

	<input type="button" name="cancel"
		value="<spring:message code="xxx.cancel"/>"
		onclick="javascript: window.location.replace('xxx/manager/list.do')" />
	<br />

</form:form>