<%-- 
    Document   : manage_pet
    Created on : Dec 4, 2019, 10:08:26 AM
    Author     : 15676439
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:import url="/includes/header.jsp">
    <c:param name="title" value="Pet Hotel | Manage Pet"></c:param>
    <c:param name="header" value="Manage Pet"></c:param>
</c:import>
<c:import url="/includes/navigation.jsp" />
    <main>
        <fieldset>
            <legend>Customer Number:<span><c:out value='${customer.customerId}'/></span></legend>
            <label id="customerName"><span><c:out value='${customer.firstName}'/> <c:out value='${customer.lastName}'/></span></label>
            <br>
            <label><span><c:out value='${customer.formattedPhone}'/></span>
                <br>
        </fieldset>
                
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
        <th>Manage</th>
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
            <td><form action="" method="get">
                    <input type="hidden" name="actionRequest"
                           value="editPet">
                    <input type="hidden" name="pet_id"
                           value="<c:out value='${pet.petId}'/>">
                    <input type="hidden" name="pet_customer_id"
                           value="<c:out value='${pet.customerId}'/>">
                    <input type="submit" value="Edit">
                </form></td>
        </tr>
    </c:forEach>
        <span id="message"><i><b><c:out value='${message}'/></b></i></span>
        
   
</table>
        <br>
        <a href="<c:url value='pet?actionRequest=anotherPet' />">Add a NEW Pet to this Customer</a>
        <br>
    </main>
<c:import url="/includes/footer.jsp" />