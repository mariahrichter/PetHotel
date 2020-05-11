<%-- 
    Document   : index
    Created on : Mar 12, 2020, 2:52:11 PM
    Author     : Owner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/includes/header.jsp">
    <c:param name="title" value="Pet Hotel | Admin"></c:param>
    <c:param name="header" value="Admin"></c:param>
</c:import>
<c:import url="/includes/navigation.jsp"/>

<main>
<button><a href="<c:url value='/petAdmin?actionRequest=manageTypes'/>">Manage Types</a></button><br>
<button><a href="<c:url value='/petAdmin?actionRequest=manageEmployees'/>">Manage Employees</a></button><br>
</main>
<c:import url="/includes/footer.jsp"/>