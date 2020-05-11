<%-- 
    Document   : manage_employees
    Created on : Mar 22, 2020, 12:34:25 PM
    Author     : Owner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/includes/header.jsp">
    <c:param name="title" value="Pet Hotel | Manage Employee"></c:param>
    <c:param name="header" value="Manage Employee"></c:param>
</c:import>
<c:import url="/includes/navigation.jsp" />

<main>
    <div>
        
        <form action="<c:url value='/petAdmin?actionRequest=generate_password'/>" method="post">
            <span id="message"><i><b><c:out value='${message}'/></b></i></span><br><br>
            <label>First Name</label>
            <input type="text" name="first_name"><br>
            <label>Last Name</label>
            <input type="text" name="last_name"><br>
            <label>Username</label>
            <input type="text" name="user_name"><br>
            <label for="select_role">Employee Role</label>
            <select name="select_role" id="select_role">
                <option value="default">Please Choose...</option>
                <c:forEach var="roleItem" items="${roleType}">
                    <option value="${roleItem.roleId}" ${roleItem.roleId == roleId ? 'selected="selected"' : ''}>
                        <c:out value="${roleItem.longDesc}" /></option>
                    </c:forEach>
            </select>
            <br>
            <input type="submit" value="Add Employee">
        </form>
            <form action="<c:url value='/petAdmin?actionRequest=clear_details'/>" method="post">
            <br>
            <br>
            <span id="resultsMessage"><i><b><c:out value='${resultsMessage}'/></b></i></span><br><br>
            <label>Employee Name: </label>
            <span><c:out value='${firstName}'/> <c:out value='${lastName}'/></span><br>
            <label>Generated Password: </label>
            <span><c:out value='${password}'/></span><br>
            <label>Role Type: </label>
            <span><c:out value='${roleTypeLongDesc}'/></span><br>
            <br>
            <br>
            <input type="submit" value="Clear Details">
            </form>
    </div>
</main>

<c:import url="/includes/footer.jsp" />