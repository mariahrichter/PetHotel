<%-- 
    Document   : employee_credentials
    Created on : Mar 22, 2020, 12:49:37 PM
    Author     : Owner
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:import url="/includes/header.jsp">
    <c:param name="title" value="Pet Hotel | Results"></c:param>
    <c:param name="header" value="Customer Pet Results"></c:param>
</c:import>
<c:import url="/includes/navigation.jsp" />
    <main>
        <span id="resultsMessage"><i><b><c:out value='${resultsMessage}'/></b></i></span><br><br>
        <label>Employee Name: </label>
        <span><c:out value='${firstName}'/> <c:out value='${lastName}'/></span><br>
        <label>Generated Password: </label>
        <span><c:out value='${password}'/></span><br>
        <label>Role Type: </label>
        <span><c:out value='${roleTypeLongDesc}'/></span><br>
        <label>Hashed Password: </label>
        <span><c:out value='${hashCompare}'/></span><br>
    </main>
<c:import url="/includes/footer.jsp" />
