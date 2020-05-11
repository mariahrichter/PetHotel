<%-- 
    Document   : pet
    Created on : Oct 3, 2019, 2:25:13 PM
    Author     : 15676439
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:import url="/includes/header.jsp">
    <c:param name="title" value="Pet Hotel | Add Pet"></c:param>
    <c:param name="header" value="Add Pet to Customer"></c:param>
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
        <form action="pet" method="post">
            <span id="message"><i><b><c:out value='${message}' default="All fields required."/></b></i></span>
        <fieldset>
            <legend>Pet</legend><br>
            <input type="hidden" name="actionRequest" value="addPet">
            <label for="pet_name">Name</label>
            <c:choose>
                <c:when test="${petName != null}">
                    <input type="text" id="pet_name" name="pet_name" 
                           value="<c:out value='${petName}'/>">
                </c:when>
                <c:otherwise>
                    <input type="text" id="pet_name" name="pet_name" 
                           value="<c:out value='${pet.petName}'/>">
                </c:otherwise>
            </c:choose>
                    <br>
            <label for="pet_type">Pet Type</label>
             <select name="pet_type" id="pet_type">
                <option value="default">Please Choose...</option>
                <c:forEach var="petItem" items="${petType}">
                    <option value="${petItem.petTypeId}" ${petItem.petTypeId == petTypeId ? 'selected="selected"' : ''}>
                        <c:out value="${petItem.longDesc}" /></option>                  
                </c:forEach>
            </select>
            <br>
            <label for="select_gender">Gender</label>
            <select name="select_gender" id="select_gender">
                <option value="default">Please Choose...</option>
                <c:forEach var="genderItem" items="${genderEnums}">
                    <option value="${genderItem}" ${genderItem == selectedGender ? 'selected="selected"' : ''}>
                        <c:out value="${genderItem}" /></option>
                </c:forEach>
            </select>
            <br>
            <label for="pet_birth">Birth Month/Year</label>
            <c:choose>
                <c:when test="${birth != null}">
                    <input type="text" id="pet_birth" name="pet_birth" 
                           value="<c:out value='${birth}'/>">
                </c:when>
                <c:otherwise>
                    <input type="text" id="pet_birth" name="pet_birth" 
                           value="<c:out value='${pet.birth}'/>">
                </c:otherwise>
            </c:choose>
            <br>
            <label for="select_dispo">Disposition</label>
             <select name="select_dispo" id="select_dispo">
                <option value="default">Please Choose...</option>
                <c:forEach var="dispoItem" items="${dispoType}">
                     <option  value="${dispoItem.dispoId}" ${dispoItem.dispoId == dispoTypeId ? 'selected="selected"' : ''}>
                         <c:out value="${dispoItem.longDesc}"/></option>                    
                </c:forEach>
            </select>
            <br>
        <label for="kennel_cough_date">Kennel Cough Date</label>
            <c:choose>
                <c:when test="${kennelCoughDate != null}">
                    <input type="date" id="kennel_cough_date" name="kennel_cough_date" 
                           value="<c:out value='${kennelCoughDate}'/>">
                </c:when>
                <c:otherwise>
                    <input type="date" id="kennel_cough_date" name="kennel_cough_date" 
                           value="<c:out value='${pet.kennelCoughDate}'/>">
                </c:otherwise>
            </c:choose>
            <br>
            <label for="pet_comments">Comments(Optional)</label>
            <c:choose>
                <c:when test="${comments !=null}">
                    <input type="text" rows="4" cols="50" id="pet_comments" name="pet_comments" 
                           value="<c:out value='${pet.comments}'/>">
                </c:when>
                <c:otherwise>
                    <input type="text" placeholder="Optional Comments for the pet" rows="4" cols="50" id="pet_comments" name="pet_comments" 
                           value="<c:out value='${pet.comments}'/>">
                </c:otherwise>
            </c:choose>
            <br>
            <br><br>
            <input type="submit" name="submit" value="Submit" id="submit">
            <br>
            <br>
            <br>
            <a href="<c:url value='pet?actionRequest=anotherCustomer' />">Add a NEW Customer</a><br><br>
            <a href="<c:url value='pet?actionRequest=anotherPet' />">Add a NEW Pet to this Customer</a>
        </fieldset>
        </form>
    </main>
<c:import url="/includes/footer.jsp" />