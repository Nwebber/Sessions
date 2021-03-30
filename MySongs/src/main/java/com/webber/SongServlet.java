package com.webber;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nathaniel Webber
 */
@WebServlet(name = "SongServlet", urlPatterns = {"/songs"})
public class SongServlet extends HttpServlet {

    Song song1 = new Song(1, "Nathan Sharp", "The Day", "https://www.youtube.com/embed/p4H5ul0cW2s");
    Song song2 = new Song(2, "Sam Luff", "Silent Solitude", "https://www.youtube.com/embed/ngOnaG0Fk08");
    Song song3 = new Song(3, "AmaLee", "Hey Kids", "https://www.youtube.com/embed/iWtTSzULPWI");
    Song song4 = new Song(4, "Jonathon Young", "Dedicate", "https://www.youtube.com/embed/EUQrXOX1FSQ");

    private final Map<Integer, Song> songList = new Hashtable<>();

    public SongServlet() {
        songList.put(1, song1);
        songList.put(2, song2);
        songList.put(3, song3);
        songList.put(4, song4);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null) {
            response.sendRedirect("login");
            return;
        }
        String action = request.getParameter("action");
        if (action == null) {
            action = "view";
        }
        switch (action) {
            case "addOne":
                addASong(request, response);
                break;
            case "playlist":
                viewPlaylist(request, response);
                break;
            case "emptyPlaylist":
                emptyPlaylist(request, response);
                break;
            case "removeSong":
                removeSong(request, response);
                break;
            default:
            case "view":
                listSongs(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null) {
            response.sendRedirect("login");
            return;
        }
        String action = request.getParameter("action");
        if (action == null) {
            action = "view";
        }
        switch (action) {
            case "addOne":
                addASong(request, response);
                break;
            case "playlist":
                viewPlaylist(request, response);
                break;
            case "emptyPlaylist":
                emptyPlaylist(request, response);
                break;
            case "removeSong":
                removeSong(request, response);
                break;
            default:
            case "view":
                listSongs(request, response);
                break;
        }
    }

    private void listSongs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("songList", songList);
        request.getRequestDispatcher("/WEB-INF/jsp/view/viewSongs.jsp").forward(request, response);
    }

    private void addASong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int songID;
        try {
            songID = Integer.parseInt(request.getParameter("songID"));
        } catch (Exception e) {
            response.sendRedirect("songs");
            return;
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("playlist") == null) {
            session.setAttribute("playlist", new Hashtable<Integer, Integer>());
        }

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> playlist = (Map<Integer, Integer>) session.getAttribute("playlist");
        if (!playlist.containsKey(songID)) {
            playlist.put(songID, 1);
        }

        response.sendRedirect("songs?action=playlist");
    }

    private void viewPlaylist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("songList", songList);
        request.getRequestDispatcher("/WEB-INF/jsp/view/playlist.jsp").forward(request, response);
    }

    private void removeSong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int songID;
        try {
            songID = Integer.parseInt(request.getParameter("songID"));
        } catch (Exception e) {
            response.sendRedirect("songs?action=playlist");
            return;
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("playlist") == null) {
            response.sendRedirect("songs?action=playlist");
            return;
        }

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> playlist = (Map<Integer, Integer>) session.getAttribute("playlist");
        if (playlist.containsKey(songID)) {
            playlist.remove(songID);
        }

        response.sendRedirect("songs?action=playlist");
    }

    private void emptyPlaylist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("playlist");
        response.sendRedirect("songs?action=playlist");
    }
}
