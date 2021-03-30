<%--@elvariable id="songID" type="java.lang.String"--%>
<%--@elvariable id="song" type="com.webber.Song"--%>

<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<%
    String contextPath = request.getContextPath();
%>
<html>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@300;400&display=swap" rel="stylesheet">
    <link href="<%= contextPath%>/styles/main.css" rel="stylesheet">
    <head>
        <title>My Playlist</title>
    </head>
    <body>
        <div class="container">
            <h2>My Playlist</h2>
            <p><a href="<c:url value="/songs" />">Back to Lineup</a></p>
            <%
                @SuppressWarnings("unchecked")
                Map<Integer, Song> songList = (Map<Integer, Song>) request.getAttribute("songList");
                @SuppressWarnings("unchecked")
                Map<Integer, Integer> playlist = (Map<Integer, Integer>) session.getAttribute("playlist");
                if (playlist == null || playlist.size() == 0)
                    out.println("<p>Your playlist is empty.</p>");
                else {
            %>
            <p><a href="<c:url value="/songs?action=emptyPlaylist" />">Empty Playlist</a></p>
            <ul>
                <%
                    for (int id : playlist.keySet()) {
                %>
                <li> <%= songList.get(id)%> - <iframe width="560" height="315" 
                                                      src="<%= songList.get(id).getYoutube()%>" 
                                                       title="YouTube video player" frameborder="0" 
                                                       allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
                                                       allowfullscreen></iframe><br>
                    <a href="<c:url value="/songs">
                           <c:param name="action" value="removeSong" />
                           <c:param name="songID" value="<%= Integer.toString(id)%>"/>
                       </c:url>">Remove Song</a>
                </li>
                <%
                    }
                %>
            </ul>
            <%
                }
            %>
        </div>
    </body>
</html>
