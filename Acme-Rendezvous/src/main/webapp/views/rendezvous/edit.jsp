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



<form:form action="rendezvous/user/edit.do"
	modelAttribute="rendezvouse">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="assistants" />


	<form:label path="name">
		<spring:message code="rendezvouse.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	<br />

	<form:label path="description">
		<spring:message code="rendezvouse.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	<br />


	<form:label path="picture">
		<spring:message code="rendezvouse.picture" />:
	</form:label>
	<form:input path="picture" />
	<form:errors cssClass="error" path="picture" />
	<br />
	<br />


	<form:label path="organisedMoment">
		<spring:message code="rendezvouse.organisedMoment" />:
	</form:label>
	<form:input path="organisedMoment" placeholder="yyyy/MM/dd" />
	<form:errors cssClass="error" path="organisedMoment" />
	<br />
	<br />

	<form:label path="draftMode">
		<spring:message code="rendezvouse.draftMode" />:
	</form:label>
	<form:select id="draftMode" path="draftMode">	
		<form:option value="1" label="YES"/>
		<form:option value="0" label="NO"/> 
	</form:select>  
	<br /> 
	
		<form:label path="forAdult">
		<spring:message code="rendezvouse.forAdult" />:
	</form:label>
	<form:select id="forAdult" path="forAdult">	
		<form:option value="1" label="YES"/>
		<form:option value="0" label="NO"/> 
	</form:select>  
	<br /> 
	
		<form:label path="gps.longitude">
		<spring:message code="rendezvouse.location.longitude" />:
	</form:label>
	<form:input path="gps.longitude" />
	<form:errors cssClass="error" path="gps.longitude" />
	<br />
	
	<form:label path="gps.latitude">
		<spring:message code="rendezvouse.location.latitude" />:
	</form:label>
	<form:input path="gps.latitude" />
	<form:errors cssClass="error" path="gps.latitude" />
	<br />
	

	<form:label path="similarRendezvouses">
		<spring:message code="rendezvous.similarRendezvouses" />:
	</form:label>
	<form:select id="similarRendezvouses" path="similarRendezvouses" >
		<form:option value="0" label="----" />			
		<form:options items="${similarRendezvouses}" itemValue="id" itemLabel="name" />		
	</form:select>
	<form:errors cssClass="error" path="similarRendezvouses" />
	<br />
	<br />  

	<!-- botones -->

	<input type="submit" name="save"
		value="<spring:message code="rendezvous.save"/>" />&nbsp;
		
	<jstl:if test="${rendezvouse.id !=0 }">
		<input type="submit" name="deletevirtual"
			value="<spring:message code="rendezvous.delete"/>"
			onclick="javascript: return confirm('<spring:message code="rendezvous.confirm.delete"/>')" />&nbsp;
	</jstl:if>

	<input type="button" name="cancel"
		value="<spring:message code="rendezvous.cancel"/>"
		onclick="javascript: window.location.replace('rendezvous/user/list.do')" />
	<br />

</form:form>


