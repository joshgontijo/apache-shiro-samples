<%-- 
    Document   : index
    Created on : Dec 11, 2014, 12:36:04 AM
    Author     : Josue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        Index... go to login:
        <form action="login.jsp">
            <input type="submit" value="Login page" />
        </form>
        <form action="secured/dashboard.jsp">
            <input type="submit" value="Secured page" />
        </form>
        
          <form method="post" action="logout">
        <input type="submit" value="Logout" />
    </form>
    </body>
</html>
