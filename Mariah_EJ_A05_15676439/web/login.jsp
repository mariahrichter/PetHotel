<%-- 
    Document   : login
    Created on : Mar 12, 2020, 2:18:13 PM
    Author     : Owner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/includes/header.jsp">
    <c:param name="title" value="Pet Hotel | Admin Login"></c:param>
    <c:param name="header" value="Admin Login"></c:param>
</c:import>
<c:import url="/includes/navigation.jsp" />

    <main>
        <div>
        <form action="<c:url value='/authenticate?actionRequest=auth_login'/>" method="post">
            <label>Username</label>
            <input type="text" name="username"><br>
            <label>Password</label>
            <input type="password" name="password"><br>
            <input type="submit" value="Login">
        </form>
        </div>
    </main>
        
<c:import url="/includes/footer.jsp" />