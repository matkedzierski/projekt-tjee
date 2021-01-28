<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.IntStream"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Base64"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c"  uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Style/galeria.css"/>
        <title>Galeria Emotikon</title>
    </head>
    <body>
        <jsp:useBean class="emotki.Kontroler" id="kontroler" scope="session"/>
        <% if(kontroler.getPort()==null) {
            kontroler.setStatus("Nie udało się nawiązać połączenia z serwerem");
            response.sendRedirect("error.jsp");
        } %>
        <h1>Galeria Emotikon</h1>
        <div class="gallery">
            <c:forEach varStatus="loop" var="emotka" items="${kontroler.map}" >
                <div class="emote_cont">
                    <a href="display.jsp?image=${emotka.key}">
                        <img src="data:image/png;base64,${emotka.value}" alt="ERR" class="emote"/>
                    </a>
                </div>
            </c:forEach>
            
            
            <div class="add_cont">
                <a href="upload.jsp">
                    <img src="img/add.png" class="emote"/>
                </a>
            </div>
            
        </div>


    </body>
</html>
