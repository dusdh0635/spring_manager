<%--
  Created by IntelliJ IDEA.
  User: dusdh
  Date: 2022-03-29
  Time: 오전 1:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- paging -->
<div class="paging">
    <a href="/portal/manage/managerList/${pageOption.firstPage}"><img src="/images/mone/btn_paging_first.gif" alt="first" /></a>
    <a href="/portal/manage/managerList/${(pageOption.nowPage-1) > pageOption.firstPage ? (pageOption.nowPage-1) : pageOption.firstPage}"><img src="/images/mone/btn_paging_prev.gif" alt="prev" /></a>
    <span class="page_num">
                    <c:forEach var = "cnt" begin="${pageOption.firstPage}" end="${pageOption.lastPage}">
                        <c:choose>
                            <c:when test="${cnt eq pageOption.nowPage}">
                                <a href="/portal/manage/managerList/${cnt}"class="on">${cnt}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="/portal/manage/managerList/${cnt}" >${cnt}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
				</span>
    <a href="/portal/manage/managerList/${(pageOption.nowPage+1) < pageOption.lastPage ? (pageOption.nowPage+1) : pageOption.lastPage}"><img src="/images/mone/btn_paging_next.gif" alt="next" /></a>
    <a href="/portal/manage/managerList/${pageOption.lastPage}"><img src="/images/mone/btn_paging_last.gif" alt="last" /></a>
</div>

</body>
</html>
