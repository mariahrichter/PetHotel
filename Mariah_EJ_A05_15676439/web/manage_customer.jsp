<%-- 
    Document   : manage_customer
    Created on : Dec 4, 2019, 10:08:13 AM
    Author     : 15676439
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:import url="/includes/header.jsp">
    <c:param name="title" value="Pet Hotel | Manage Customer"></c:param>
    <c:param name="header" value="Manage Customer"></c:param>
</c:import>
<c:import url="/includes/navigation.jsp" />
    <main>
        <table>
    <tr>
        <th>Id</th>
        <th class="fullName">Name</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Address</th>
        <th>City</th>
        <th>State</th>
        <th>Postal Code</th>
        <th>Status</th>
        <th>View Pets</th>
        <th>Manage</th>
    </tr>

    <c:forEach var="customer" items="${customerList}">
        <tr>
            <td><c:out value="${customer.customerId}"/></td>
            <td class="fullName"><c:out value="${customer.firstName}"/> <c:out value="${customer.lastName}"/></td>
            <td><c:out value="${customer.phone}"/></td>
            <td><c:out value="${customer.email}"/></td>
            <td><c:out value="${customer.streetAddress}"/></td>
            <td><c:out value="${customer.city}"/></td>
            <td><c:out value="${customer.stateShortDesc}"/></td>
            <td><c:out value="${customer.postalCode}"/></td>
            <td><c:out value="${customer.active}"/></td>
            <td><form action="" method="get">
                    <input type="hidden" name="actionRequest"
                           value="viewPets">
                    <input type="hidden" name="customer_id"
                           value="<c:out value='${customer.customerId}'/>">
                    <input type="submit" value="Pets">
                </form></td>
            <td><form action="" method="get">
                    <input type="hidden" name="actionRequest"
                           value="editCustomer">
                    <input type="hidden" name="customer_id"
                           value="<c:out value='${customer.customerId}'/>">
                    <input type="submit" value="Edit">
                </form></td>
        </tr>
    </c:forEach>
        <span id="message"><i><b><c:out value='${message}'/></b></i></span>
   
</table>
    </main>
<c:import url="/includes/footer.jsp" />
