<%-- 
    Document   : results
    Created on : Oct 3, 2019, 2:25:25 PM
    Author     : 15676439
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:import url="/includes/header.jsp">
    <c:param name="title" value="Pet Hotel | Results"></c:param>
    <c:param name="header" value="Customer Pet Results"></c:param>
</c:import>
<c:import url="/includes/navigation.jsp" />
    <main>
        <p id="date"><b><c:out value='${requestScope.date}'/></b></p>
        <label>Customer ID: </label>
        <span><c:out value='${customer.customerId}'/></span><br>
        <label>First Name: </label>
        <span><c:out value='${customer.firstName}'/></span><br>
        <label>Last Name: </label>
        <span><c:out value='${customer.lastName}'/></span><br>
        <label>Phone: </label>
        <span><c:out value='${customer.phone}'/></span><br>
        <label>Email: </label>
        <span><c:out value='${customer.email}'/></span>
        <br><br>
        <label>Pet Name: </label>
        <span><c:out value='${pet.petName}'/></span><br>
        <label>Pet Type: </label>
        <span><c:out value='${petTypeLongDesc}'/></span><br>
        <label>Gender: </label>
        <span><c:out value='${pet.gender}'/></span><br>
        <label>Birth Month/Year: </label>
        <span><c:out value='${pet.birth}'/></span><br>
        <label>Disposition: </label>
        <span><c:out value='${dispoTypeLongDesc}'/></span><br>
        <label>Date of Last Kennel Cough: </label>
        <span><c:out value='${pet.kennelCoughDate}'/></span><br>
    </main>
<c:import url="/includes/footer.jsp" />