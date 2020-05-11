<%-- 
    Document   : customer
    Created on : Oct 3, 2019, 2:25:05 PM
    Author     : 15676439
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:import url="/includes/header.jsp">
    <c:param name="title" value="Pet Hotel | Add Customer"></c:param>
    <c:param name="header" value="Add Customer"></c:param>
</c:import>
<c:import url="/includes/navigation.jsp" />
    <main>
        <span id="message"><i><b><c:out value='${message}' default="All fields required."/></b></i></span>
        <%--add code for form --%>
        <form action="pet" method="Post">
        <fieldset>
            <legend>Customer</legend><br>
            <input type="hidden" name="actionRequest" value="addCustomer">
            <br>
            <label for="first_name">First Name</label>
            <c:choose>
                <c:when test="${firstName != null}">
                    <input type="text" id="first_name" name="first_name" 
                           value="<c:out value='${firstName}'/>">
                </c:when>
                <c:otherwise>
                    <input type="text" id="first_name" name="first_name" 
                           value="<c:out value='${customer.firstName}'/>">
                </c:otherwise>
            </c:choose>
            <br>
            <label for="last_name">Last Name</label>
            <c:choose>
                <c:when test="${lastName !=null}">
                    <input type="text" id="last_name" name="last_name" 
                           value="<c:out value='${lastName}'/>">
                </c:when>
                <c:otherwise>
                    <input type="text" id="last_name" name="last_name" 
                           value="<c:out value='${customer.lastName}'/>">
                </c:otherwise>
            </c:choose>
            <br>
            <label for="phone">Phone</label>
            <c:choose>
                <c:when test="${phone != null}">
                    <input type="text" id="phone" name="phone" 
                           value="<c:out value='${phone}'/>">
                </c:when>
                <c:otherwise>
                    <input type="text" id="phone" name="phone" 
                           value="<c:out value='${customer.phone}'/>">
                </c:otherwise>
            </c:choose>
            <br>
            <label for="street_address">Street Address</label>
            <c:choose>
                <c:when test="${streetAddress != null}">
                    <input type="text" id="street_address" name="street_address" 
                           value="<c:out value='${streetAddress}'/>">
                </c:when>
                <c:otherwise>
                    <input type="text" id="street_address" name="street_address" 
                           value="<c:out value='${customer.streetAddress}'/>">
                </c:otherwise>
            </c:choose>
            <br>
            <label for="city">City</label>
            <c:choose>
                <c:when test="${city != null}">
                    <input type="text" id="city" name="city" 
                           value="<c:out value='${city}'/>">
                </c:when>
                <c:otherwise>
                    <input type="text" id="city" name="city" 
                           value="<c:out value='${customer.city}'/>">
                </c:otherwise>
            </c:choose>
            <br>
            <label for="state">State</label>
             <select name="state" id="state">
                <option value="default">Please Choose...</option>
                <c:forEach var="stateType" items="${stateType}">
                     <option value="${stateType.stateId}" ${stateType.stateId == stateTypeId ? 'selected="selected"' : ''}>
                         <c:out value="${stateType.longDesc}"/></option>                    
                </c:forEach>
            </select>
            <br>
            <label for="postal_code">Postal Code</label>
            <c:choose>
                <c:when test="${postalCode != null}">
                    <input type="text" id="postal_code" name="postal_code" 
                           value="<c:out value='${postalCode}'/>">
                </c:when>
                <c:otherwise>
                    <input type="text" id="postal_code" name="postal_code" 
                           value="<c:out value='${customer.postalCode}'/>">
                </c:otherwise>
            </c:choose>
            <br>
            <label for="email">Email</label>
            <c:choose>
                <c:when test="${email !=null}">
                    <input type="text" id="email" name="email" 
                           value="<c:out value='${email}'/>">
                </c:when>
                <c:otherwise>
                    <input type="text" id="email" name="email" 
                           value="<c:out value='${customer.email}'/>">
                </c:otherwise>
            </c:choose>
            <br>
            <label for="customer_comments">Comments(Optional)</label>
            <c:choose>
                <c:when test="${comments !=null}">
                    <input type="text" rows="4" cols="50" id="customer_comments" name="customer_comments" 
                           value="<c:out value='${customer.comments}'/>">
                </c:when>
                <c:otherwise>
                    <input type="text" placeholder="Optional Comments for the customer" rows="4" cols="50" id="customer_comments" name="customer_comments" 
                           value="<c:out value='${customer.comments}'/>">
                </c:otherwise>
            </c:choose>
            <br>
            <input type="submit" name="submit" value="Submit" id="submit">
        </fieldset>
        </form>
                
    </main>
<c:import url="/includes/footer.jsp" />
