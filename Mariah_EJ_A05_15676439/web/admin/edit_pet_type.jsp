<%-- 
    Document   : editPetType
    Created on : Mar 12, 2020, 4:21:10 PM
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
    <h2>Pet Type:</h2>
    <div>
        <form action="<c:url value='/petAdmin?actionRequest=update_pet_type'/>" method="post">
                    <label for="short_desc">Short Description:</label>
                    <c:choose>
                        <c:when test="${shortDesc != null}">
                            <input type="text" id="short_desc" name="short_desc"
                                   value="<c:out value='${shortDesc}'/>">
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="short_desc" name="short_desc"
                                   value="<c:out value='${petType.shortDesc}'/>">
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
                                   value="<c:out value='${petType.longDesc}'/>">
                        </c:otherwise>
                    </c:choose><br>
                    <br>
                    <input type="hidden" name="pet_type_id" value="${petType.petTypeId}" />
                    <input type="hidden" name="actionRequest" value="update_pet_type" />
                    <input type="submit" value="submit"><br><br>
        </form>
                    <span><i><c:out value="${message}" default="All fields are required"/></i></span><br>
    </div>
</main>    

<c:import url="/includes/footer.jsp" />
