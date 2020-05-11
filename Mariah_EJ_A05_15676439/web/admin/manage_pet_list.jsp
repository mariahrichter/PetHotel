<%-- 
    Document   : manage_pets
    Created on : Mar 18, 2020, 7:21:03 PM
    Author     : Owner
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:import url="/includes/header.jsp">
    <c:param name="title" value="Pet Hotel | Manage Pets"></c:param>
    <c:param name="header" value="Manage Pets"></c:param>
</c:import>
<c:import url="/includes/navigation.jsp" />
<main>
    <table>
        <tr>
            <th>Id</th>
            <th>Pet Name</th>
            <th>Gender</th>
            <th>Pet Type</th>
            <th>Born</th>
            <th>Disposition</th>
            <th>Kennel Cough</th>
            <th>Status</th>
        </tr>

        <c:forEach var="pet" items="${petList}">
            <tr>
                <td><c:out value="${pet.petId}"/></td>
                <td><c:out value="${pet.petName}"/></td>
                <td><c:out value="${pet.gender}"/></td>
                <td><c:out value="${pet.petTypeShortDesc}"/></td>
                <td><c:out value="${pet.birth}"/></td>
                <td><c:out value="${pet.dispoTypeShortDesc}"/></td>
                <td><c:out value="${pet.kennelCoughDate}"/></td>
                <td><c:out value="${pet.active}"/></td>
            </tr>
        </c:forEach>
        <span id="message"><i><b><c:out value='${message}'/></b></i></span>

    </table>
</main>
<c:import url="/includes/footer.jsp" />
