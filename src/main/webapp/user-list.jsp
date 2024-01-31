<%@ page contentType="text/html;charset=UTF-8" language="java"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        h1 {
            color: #3e9158;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tbody tr:hover {
            background-color: #f5f5f5;
        }

        button {
            margin-bottom: 20px;
        }

        a {
            text-decoration: none;
            color: #007bff;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1>List of Users in the System</h1>
<button type="button">
    <a href="user-form.jsp">Add a User</a>
</button>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Country</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="users" scope="request" type="java.util.List"/>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.getId()}"></c:out></td>
            <td><c:out value="${user.getName()}"></c:out></td>
            <td><c:out value="${user.getEmail()}"></c:out></td>
            <td><c:out value="${user.getCountry()}"></c:out></td>
            <td>
                <a href="editScreen-user?id=<c:out value='${user.getId()}'/>">Edit</a>
                &nbsp;&nbsp;
                <a href="delete-user?id=<c:out value='${user.getId()}'/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
