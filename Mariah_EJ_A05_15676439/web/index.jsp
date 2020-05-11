<%-- 
    Document   : index
    Created on : Oct 3, 2019, 2:25:37 PM
    Author     : 15676439
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:import url="/includes/header.jsp">
    <c:param name="title" value="Pet Hotel | Home"></c:param>
    <c:param name="header" value="Mariah's Pet Hotel"></c:param>
</c:import>
<c:import url="/includes/navigation.jsp"/>
    <main>
        
        <form action="pet" method="GET">
            <input type="hidden" name="actionRequest" value="searchCustomers">
        <fieldset>
            <legend><b>Search by last name, phone, or all customers</b></legend>
            <br>
            <input type="radio" name="search" value="phone"> Phone
            &nbsp;
            <input type="radio" name="search" value="lastName"> Last Name
            &nbsp;
            <input type="radio" name="search" value="allCustomers" checked> All Customers
            <br>
            <br>
            <input type="text" name="searchTextBox">
            <br>
            <br>
            <input type="submit" name="search" value="Search" id="search">
        </fieldset>
        </form>
                
    </main>
<c:import url="/includes/footer.jsp" />