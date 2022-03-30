<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="eng" lang="eng">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Content-Script-Type" content="text/javascript" />
    <meta http-equiv="Content-Style-Type" content="text/css" />
    <title>Manager</title>
    <link rel="stylesheet" type="text/css" href="/css/default.css" />
    <link rel="stylesheet" type="text/css" href="/css/guide.css" />
    <link rel="stylesheet" type="text/css" href="/css/content.css" />
    <script type="text/javascript" src="/scripts/jquery-ui/jquery.min.js"></script>
    <script type="text/javascript" src="/scripts/common/common-ui.js"></script>
    <style type="text/css">
    </style>
</head>
<body>
<div id="wrap">
    <!-- header -->
    <div id="header"></div>
    <!-- header -->

    <!-- container -->
    <div id="container">

        <!-- lnb -->
        <div class="lnb"></div>
        <!-- lnb -->

        <!-- contents -->
        <div class="contents">

            <!-- location -->
            <div class="location"><a href="#">Home</a> &gt; <a href="#">Admin</a> &gt; <a href="#"><span class="txt_w">Manager</span></a></div>
            <!-- location -->

            <div class="title">
                <span class="brd_rtop mgb10">
					<span class="btn_orange"><a href="/portal/manage/managerWrite">Add New Manager</a></span>
                </span>
            </div>

            <!-- search -->
            <form action="/portal/manage/managerList/search" method="post">
            <div class="search">
                <strong>Manager Group</strong>
                <select name="managerGroup" style="width:180px;">
                    <option>${searchOption.managerGroup}</option>
                    <c:forEach var = "group_name" items="${groupList}">
                        <option>${group_name}</option>
                    </c:forEach>
                </select>
                <strong class="mgl50">Status</strong>
                <select name="status" style="width:180px;">
                    <option>${searchOption.status}</option>
                    <option>Active</option>
                    <option>Inactive</option>
                </select><br />
                <strong>Factor Name</strong>
                <select name="factorName" style="width:180px;">
                    <option>${searchOption.factorName}</option>
                    <option>Login ID</option>
                    <option>User Name</option>
                    <option>Employee No.</option>
                </select>
                <input name="keyword" type="text" value="${searchOption.keyword}" class="keyword" title="keyword" style="width:238px;" />
                <button class="btn_search" type="submit">Search</button>
            </div>
            </form>
            <!-- search -->

            <!-- table -->
            <div id="table">
                <div class="title mgt20">
                    <h3>Manager List</h3>
                    <span class="brd_rtop">
                        Total List <span class="num">${total}</span>
                    </span>
                </div>

                <table class="brd">
                    <caption>Manager List</caption>
                    <colgroup>
                        <col width="10%" />
                        <col width="*" />
                        <col width="20%" />
                        <col width="12%" />
                        <col width="20%" />
                        <col width="10%" />
                    </colgroup>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Manager Group</th>
                        <th>Tel.</th>
                        <th>E-mail</th>
                        <th>Edit</th>
                    </tr>
                    </thead>
                    <%@ include file="/WEB-INF/views/common/paging.jsp" %>
                </table>
                <%@ include file="/WEB-INF/views/common/pageButton.jsp" %>
                    <!-- table -->
            </div>

        </div>
        <!-- contents -->

    </div>
    <!-- container -->

    <!-- footer -->
    <div id="footer"></div>
    <!-- footer -->

</div>
</body>
</html>
