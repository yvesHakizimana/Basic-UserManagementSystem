<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <h1>User Management System</h1>

        <ul class="navbar-nav">
            <li>
                <a href="./user-list.jsp">UserList
                  </a>
            </li>
        </ul>
        </div>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
<%--            <jsp:useBean id="user" scope="request" type="com.code.empcrud.empcrud.model.User"/>--%>
            <c:if test="${user != null}">
            <form action="update-user" method="post">
                </c:if>
                <c:if test="${user == null}">
                <form action="register-user" method="post">
                    </c:if>


                    <caption>
                        <h2>
                            <c:if test="${user != null}">
                                Edit User
                            </c:if>
                            <c:if test="${user == null}">
                                Add New User
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${user != null}">
                        <input type="hidden" name="id" value="<c:out value='${user.getId()}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>User Name</label> <input type="text"
                                                        value="<c:out value='${user.getName()}' />" class="form-control"
                                                        name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>User Email</label> <input type="text"
                                                         value="<c:out value='${user.getEmail()}' />" class="form-control"
                                                         name="email">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>User Country</label> <input type="text"
                                                           value="<c:out value='${user.getCountry()}' />" class="form-control"
                                                           name="country">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>