<%@ page import="java.util.List" %>
<%@ page import="models.view.HomeViewModel" %>
<%@ page import="models.entity.Course" %>
<%@ page import="models.service.CourseServiceModel" %><%--
  Created by IntelliJ IDEA.
  User: silvia
  Date: 15.10.2019 г.
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>It works</h1>
<strong>
  <%= ((HomeViewModel) request.getAttribute("viewModel")).getName()%>
</strong>

<% for(CourseServiceModel course : ((HomeViewModel)request.getAttribute("viewModel")).getCourses()){ %>
    <li>
        <%=course.getName()%>
    </li>

<% }%>
</body>
</html>
