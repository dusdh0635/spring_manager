<%--
  Created by IntelliJ IDEA.
  User: dusdh
  Date: 2022-03-29
  Time: 오전 1:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<tbody>
<div id = "page_list">
<c:forEach var = "manager" items="${list}" begin="${pageOption.start}" end="${pageOption.end}">
    <tr>
        <td> ${manager.USER_ID}</td>
        <td> ${manager.USER_NAME}</td>
        <td> ${manager.USER_GROUP_NAME}</td>
        <td> ${manager.TEL_NO}</td>
        <td> ${manager.e_MAIL}</td>
        <td><span class="btn_s_green"><a href="/portal/manage/managerUpdate/${manager.USER_ID}">Edit</a></span></td>
    </tr>
</c:forEach>
</div>
</tbody>
</body>
</html>
