<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<%
    @SuppressWarnings("unchecked")
            Map<Integer, Song> songList = (Map<Integer, Song>) request.getAttribute("songList");
%>
<%
    String contextPath = request.getContextPath();
%>
<html>
    <head>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Lato:wght@300;400&display=swap" rel="stylesheet">
        <link href="<%= contextPath %>/styles/main.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Songs</title>
    </head>
    <body>
        <div class="container">
            <h2>Current Lineup</h2><br>
            <ul>
                <%
                for (int id : songList.keySet()) {
                %>
                <li><%= songList.get(id) %>
                    <a href="<c:url value="/songs">
                               <c:param name="action" value="addOne" />
                               <c:param name="songID" value="<%= Integer.toString(id)%>"/>
                               </c:url>">Add to Playlist</a></li>
                <%
                    }
                %>
            </ul>
            <p><a href="<c:url value="/songs?action=playlist" />">View Playlist</a></p>
            <a href="<c:url value="/login?logout" />">Logout</a><br>
        </div>
    </body>
</html>
