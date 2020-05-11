<%-- 
    Document   : header
    Created on : Oct 3, 2019, 2:26:46 PM
    Author     : 15676439
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <head>
        <link rel="icon" type="image/png" href="images/pawprint_favicon.png" />
        <link rel="icon" type="image/png" href="../images/pawprint_favicon.png" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value='${param.title}'/></title>
        <link rel="stylesheet" href="<c:url value='styles/main.css' />">
        <link rel="stylesheet" href="<c:url value='../styles/main.css' />">
    </head>
    <body>
    <header>
        <h1><c:out value='${param.header}'/></h1>
    </header>
