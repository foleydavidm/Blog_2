<%--
  Created by IntelliJ IDEA.
  User: Manifest
  Date: 6/22/2016
  Time: 2:15 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <meta name="layout" content="main"/>
            <title>Todo Find</title>
    </head>

<body>
     <h2>
         Share your thoughts!
     </h2>
    <g:form controller="todo" method="post">
        <input type='text' name='value' value="${value}"/>
        <g:actionSubmit value="Submit Blog Post"/>
    </g:form>

</body>
</html>