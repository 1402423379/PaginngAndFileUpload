<%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 2022/4/9
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        table{
            border: 1px red solid;
            margin:0px auto;
            width: 80%;
        }
        td,th{
            border:1px solid green;
        }
    </style>
</head>
<body>
<table cellpadding="0px" cellspacing="0px">
    <tr>
        <th>学生编号</th>
        <th>学生姓名</th>
        <th>学生年龄</th>
        <th>学生性别</th>
        <th>照片</th>
        <th>照片类型</th>
        <th>下载</th>
    </tr>
    <%--//当前页数据
    private List<T> data;
    //总记录数
    private int totalSize;
    //总页数
    private int totalPage;
    //当前页记录数
    private int pageSize;
    //当前页数
    private int currentPage;--%>
    <c:forEach items="${pageBean.data}" var="student">
        <tr>
            <td>${student.stuid}</td>
            <td>${student.stuname}</td>
            <td>${student.stuage}</td>
            <td>${student.stugender}</td>
            <td>${student.filename}</td>
            <td>${student.filetype}</td>
            <td>
                <a href="#">删除</a>
            </td>
        </tr>
    </c:forEach>
    <tr align="center">
        <td colspan="7" >
            <a href="javascript:void(0)" onclick="changePage(${pageBean.currentPage-1})">上一页</a>
            &nbsp;&nbsp;&nbsp;
            <c:forEach begin="1" end="${pageBean.totalPage}" var="num">
                <c:choose>
                    <c:when test="${num eq pageBean.currentPage}">
                        [${num}]
                    </c:when>
                    <c:otherwise>
                        ${num}
                    </c:otherwise>
                </c:choose>

            </c:forEach>
            <a href="javascript:void(0)" onclick="changePage(${pageBean.currentPage+1})">下一页</a>
            &nbsp;&nbsp;&nbsp;
            尾页&nbsp;&nbsp;&nbsp;
            每页<input id="pageSize" type="text" style="width: 25px" value="${pageBean.pageSize}">条&nbsp;&nbsp;&nbsp;
            当前第${pageBean.currentPage}页&nbsp;&nbsp;&nbsp;
            共${pageBean.totalPage}页&nbsp;&nbsp;&nbsp;
            共${pageBean.totalSize}条记录
        </td>
    </tr>
</table>
<script src="js/jquery.min.js"></script>
<script>
    function changePage(currentPage) {
        if(currentPage<1){
            alert("当前已经是第一页");
            return;
        }
        if(currentPage>${pageBean.totalPage}){
            alert("当前已经是最后一页");
            return;
        }

        window.location.href="StudentServletController.do?currentPage="+currentPage+"&pageSize="+$("#pageSize").val();
    }

</script>
</body>
</html>
