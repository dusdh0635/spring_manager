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

            <!-- table -->
            <div class="title">
                <span class="brd_rtop mgb10">
					<span class="btn_orange"><a href="/portal/manage/managerList/">List</a></span>
                </span>
            </div>

            <form action="/portal/manage/managerWrite/new" method="post">
            <!-- table_detail -->
            <table class="brd_detail">
                <caption>Manager Write</caption>
                <colgroup>
                    <col width="25%" />
                    <col width="75%" />
                </colgroup>
                <tbody>
                <tr>
                    <th>ID</th>
                    <td><input name="USER_ID" type="text" class="" style="width:188px;"/><span class="btn_gray mgl5"><a href="/portal/manage/managerWrite/checkId/"+${USER_ID}>Check ID</a></span></td>
                </tr>
                <tr>
                    <th>Manager Name <img src="/images/mone/star.gif" alt=""/></th>
                    <td><input name="USER_NAME" type="text" class="" style="width:188px;"/></td>
                </tr>
                <tr>
                    <th>Manager Group <img src="/images/mone/star.gif" alt=""/></th>
                    <td>
                        <select name="USER_GROUP_ID" style="width:200px;">
                            <option>ManagerGroup</option>
                            <c:forEach var = "group_name" items="${group_list}">
                                <option>${group_name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Password <img src="/images/mone/star.gif" alt=""/></th>
                    <td><input name="PASSWORD" type="text" class="" style="width:188px;"/></td>
                </tr>
                <tr>
                    <th>Retype Password <img src="/images/mone/star.gif" alt=""/></th>
                    <td><input name="PASSWORD2" type="text" class="" style="width:188px;"/></td>
                </tr>
                <tr>
                    <th>Department</th>
                    <td><input name="DEPT_NAME" type="text" class="" style="width:188px;"/></td>
                </tr>
                <tr>
                    <th>Employee No.</th>
                    <td><input name="EMP_NO" type="text" class="" style="width:188px;"/></td>
                </tr>
                <tr>
                    <th>Tel.</th>
                    <td><input name="TEL_NO" type="text" class="" style="width:188px;"/></td>
                </tr>
                <tr>
                    <th>E-mail <img src="/images/mone/star.gif" alt=""/></th>
                    <td><input name="E_MAIL" type="text" class="" style="width:188px;"/></td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td>
                        <select name="USE_YN" style="width:200px;">
                            <option>Select Item</option>
                            <option>Active</option>
                            <option>Inactive</option>
                        </select>
                    </td>
                </tr>
                </tbody>
            </table>

            <div class="brd_btn">
				<span class="left">
					<input class="btn_gray2" type="reset">Reset</input>
				</span>
                <span class="right">
					<input class="btn_orange" type="submit">Save</input>
				</span>
            </div>
            </form>
            <!-- table_detail -->

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
