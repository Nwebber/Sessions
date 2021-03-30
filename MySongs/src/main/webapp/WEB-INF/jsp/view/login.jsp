<!DOCTYPE html>
<%
    String contextPath = request.getContextPath();
%>
<html>
    <head>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Lato:wght@300;400&display=swap" rel="stylesheet">
        <link href="<%= contextPath %>/styles/main.css" rel="stylesheet">
        <title>My Songs</title>
    </head>
    <body>
        <div class="container">
            <h2>Login</h2>
            <p>You must log in to access the My Songs site.</p>
            <form method="POST" action="<c:url value="/login" />">
                <label for="username">Username</label>
                <input type="text" name="username" id="username" /><br><br>
                <label for="password">Password</label>
                <input type="password" name="password" id="password" /><br><br>
                <input type="submit" value="Log In" />
            </form>
            <%
                if ((Boolean) request.getAttribute("loginFailed")) {
            %>
            <p style="font-weight: bold; color: red">The username or password you entered are not correct. Please try again.</p>
            <%
                }
            %>
        </div>
    </body>
</html>
