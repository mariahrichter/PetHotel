<%-- 
    Document   : manage_types
    Created on : Mar 12, 2020, 3:06:18 PM
    Author     : Owner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/includes/header.jsp">
    <c:param name="title" value="Pet Hotel | Manage Types"></c:param>
    <c:param name="header" value="Manage Types"></c:param>
</c:import>
<c:import url="/includes/navigation.jsp"/>

 <main>
<div class="manageDispo">

<table>
    <tr>
        <th>Id</th>
        <th>Short Description</th>
        <th>Long Description</th>
        <th>Status</th>
    </tr>

    <c:forEach var="dispo" items="${dispoType}">
        <tr>
            <td><c:out value="${dispo.dispoId}"/></td>
            <td><c:out value="${dispo.shortDesc}"/></td>
            <td><c:out value="${dispo.longDesc}"/></td>
            <td><c:out value="${dispo.active}"/></td>
        </tr>
    </c:forEach>
        <span id="message"><i><b><c:out value='${message}'/></b></i></span>
   
</table>
</div>
<div class="managePetTypes">
        <table>
    <tr>
        <th>Id</th>
        <th>Short Description</th>
        <th>Long Description</th>
        <th>Status</th>
        <th></th>
    </tr>

    <c:forEach var="pet" items="${petType}">
        <tr>
            <td><c:out value="${pet.petTypeId}"/></td>
            <td><c:out value="${pet.shortDesc}"/></td>
            <td><c:out value="${pet.longDesc}"/></td>
            <td><c:out value="${pet.active}"/></td>
            <td>
                <form action="" method="get">
                    <input type="hidden" name="actionRequest" value="show_petType_edit">
                    <input type="hidden" name="pet_type_id" value="${pet.petTypeId}">
                    <input type="submit" value="Edit">
                </form>
            </td>
        </tr>
    </c:forEach>
        <tr>
            <td>New</td>
        <form action="" method="get">
            <td><input type="text" id="new_short_desc" name="new_short_desc"></td>
            <td><input type="text" id="new_long_desc" name="new_long_desc"></td>
            <td name="isActive" value="1">True</td>
            <td>
                <input type="hidden" name="actionRequest" value="add_another_petType">
                <input type="submit" value="Add Pet">
            </td>
        </form>
        </tr>
        <span id="message"><i><b><c:out value='${message}'/></b></i></span>
   
</table>
</div>
        <div class="managePetTypes">
        <table>
    <tr>
        <th>Id</th>
        <th>Short Description</th>
        <th>Long Description</th>
        <th>Status</th>
        <th></th>
    </tr>

    <c:forEach var="state" items="${stateType}">
        <tr>
            <td><c:out value="${state.stateId}"/></td>
            <td><c:out value="${state.shortDesc}"/></td>
            <td><c:out value="${state.longDesc}"/></td>
            <td><c:out value="${state.active}"/></td>
            <td>
                <form action="" method="get">
                    <input type="hidden" name="actionRequest" value="show_stateType_edit">
                    <input type="hidden" name="state_id" value="${state.stateId}">
                    <input type="submit" value="Edit">
                </form>
            </td>
        </tr>
    </c:forEach>
        <tr>
            <td>New</td>
        <form action="" method="get">
            <td><input type="text" id="new_short_desc" name="new_short_desc"></td>
            <td><input type="text" id="new_long_desc" name="new_long_desc"></td>
            <td name="isActive" value="1">True</td>
            <td>
                <input type="hidden" name="actionRequest" value="add_another_stateType">
                <input type="submit" value="Add State">
            </td>
        </form>
        </tr>
        <span id="message"><i><b><c:out value='${message}'/></b></i></span>
   
</table>
</div>
    </main>


<c:import url="/includes/footer.jsp"/>
