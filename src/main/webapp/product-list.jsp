<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/6/2023
  Time: 6:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<section class="flex flex-col gap-2 px-3">
    <div class="flex flex-rox">Product List ::</div>
    <hr>
    <div class="table-auto table w-full">
        <c:forEach items="${requestScope.products}" var="p" varStatus="vs">
            <div class="table-row">
                <div class="table-cell text-left">${vs.count + (page - 1)*pageSize})</div>
                <div class="table-cell text-left">${p.productCode}: ${p.productName}</div>
                <div class="table-cell text-left"> ${p.productScale}</div>
            </div>
        </c:forEach>
    </div>
    <hr>
    <div>
        <div>Page ::</div>
        <div class="flex gap-1 rounded-full">
            <c:forEach begin="1" end="${itemCount/pageSize + (itemCount%pageSize>0?1 :0)}" varStatus="vs">
                <c:choose>
                    <c:when test="${vs.count == page}">
                        <div class="p-1 bg-red-500">[${vs.count}]</div>
                    </c:when>
                    <c:otherwise>
                        <div class="p-1 cursor-pointer bg-red-400">
                            <a href="product-list?pageSize=${pageSize}&page=${vs.count}">[${vs.count}]</a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </div>
</section>
</body>
</html>
