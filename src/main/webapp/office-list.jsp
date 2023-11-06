<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: FIRST-ACER-Desktop
  Date: 11/6/2023
  Time: 10:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<section class="p-5">
    <h2 class="text-xl font-bold">Classic Model Offices ::</h2>
    <div class="flex gap-3 flex-wrap mb-4">
        <c:forEach var="office" items="${requestScope.offices}">
            <div class="rounded-full border border-1 p-4 ">
                <div>
                        ${office.city}
                    <a href="office-list?officeCode=${office.officeCode}" class="text-red-500 underline">
                            ${office.city}
                    </a>, ${office.country}
                </div>
                <div>${office.phone}</div>
            </div>
        </c:forEach>
    </div>
    <hr>
    <c:if test="${requestScope.selectedOffice != null}">
        <div class="flex flex-col gap-4 mt-4">
            <h2 class="text-xl font-bold">Employee ::</h2>
            <c:choose>
                <c:when test="${!requestScope.selectedOffice.employeeList.isEmpty()}">
                    <c:forEach items="${requestScope.selectedOffice.employeeList}" var="employee">
                        <div class="py-[0.3rem] px-[0.55rem] rounded-lg flex bg-red-300">
                            <h3>${employee.firstName}</h3> - <p>${employee.jobTitle}</p>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="flex flex-wrap gap-2">

                        <h3 class="text-xl text-red-500">This Office is Empty</h3>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>
</section>
</body>
</html>
