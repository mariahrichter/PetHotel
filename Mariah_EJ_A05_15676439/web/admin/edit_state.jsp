<%-- 
    Document   : editState
    Created on : Mar 18, 2020, 3:49:41 PM
    Author     : Owner
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.jsp">
    <c:param name="title" value="Pet Hotel | Edit Pet Type"></c:param>
    <c:param name="header" value="Edit Pet Type"></c:param>
</c:import>
<%@include file="/includes/navigation.jsp" %>

<main>
    <h2>State:</h2>
    <div>
        <form action="<c:url value='/petAdmin?actionRequest=update_state'/>" method="post">
                    <label for="short_desc">Short Description:</label>
                    <c:choose>
                        <c:when test="${shortDesc != null}">
                            <input type="text" id="short_desc" name="short_desc"
                                   value="<c:out value='${shortDesc}'/>">
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="short_desc" name="short_desc"
                                   value="<c:out value='${stateList.shortDesc}'/>">
                        </c:otherwise>
                    </c:choose><br>
                    <label for="long_desc">Long Description:</label>
                    <c:choose>
                        <c:when test="${longDesc != null}">
                            <input type="text" id="long_desc" name="long_desc"
                                   value="<c:out value='${longDesc}'/>">
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="long_desc" name="long_desc"
                                   value="<c:out value='${stateList.longDesc}'/>">
                        </c:otherwise>
                    </c:choose><br>
                    <br>
                    <input type="hidden" name="state_id" value="${stateList.stateId}" />
                    <input type="hidden" name="actionRequest" value="update_state" />
                    <input type="submit" value="submit"><br><br>
        </form>
                    <span><i><c:out value="${message}" default="All fields are required"/></i></span><br>
    </div>
</main>    

<c:import url="/includes/footer.jsp" />