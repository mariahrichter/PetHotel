<%-- 
    Document   : navigation
    Created on : Mar 12, 2020, 2:47:41 PM
    Author     : Owner
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <aside>
        <ul>
            <li><a href="<c:url value='/'/>">Home</a>
            <li><a href="<c:url value='/admin'/>">Admin</a>
            <li><a href="<c:url value='/'/>">Customers</a>
            <li><a href="<c:url value='/petAdmin?actionRequest=show_pet_list'/>">Pets</a>
        </ul>
    </aside>
  