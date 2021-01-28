<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c"  uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Style/galeria.css"/>
        <title>Podgląd</title>
    </head>
    <body>
        <jsp:useBean id="kontroler" scope="session" class="emotki.Kontroler"/>
            <c:choose>
                <c:when test="${kontroler.getMap().get(param.image) != null}">
                    <h2>Podgląd emotikony</h2>
                    <div class="form_cont">
                        <img src="data:image/png;base64,${kontroler.getBigImage(param.image)}" 
                         style="max-height: 400px; max-width: 400px;" alt=""/>

                        <table id="details">
                            <tr>
                                <td style="text-align: left">Opis:</td><td style="text-align: left">${kontroler.getFileInfo(param.image).get(1)}</td>
                            </tr>
                            <tr>
                                <td style="text-align: left">Dodano:</td><td style="text-align: left">${kontroler.getFileInfo(param.image).get(2)}</td>
                            </tr>
                        </table>
                            
                    <form action="galeria.jsp" method="post" >
                        <input type="submit" value="Powrót" class="button" style="float: left"/>
                    </form>

                    <form action="DownloadAction" method="get" >
                        <input type="submit" value="Pobierz" class="button" style="float:left"/>
                        <input type="hidden" value="${param.image}" name="image"/>
                    </form>

                    <form action="DeleteAction" method="post">
                        <input type="submit" value="Usuń" class="button" style="float:right"/>
                        <input type="hidden" value="${param.image}" name="image"/>
                    </form>
                    </div>

                        
                </c:when>
                <c:otherwise>
                    <div class="form_cont">
                        <h2>Ta emotka została usunięta!</h2>
                        <form action="galeria.jsp" method="post" style="text-align: center;">
                            <input type="submit" value="Powrót" class="button"/>
                        </form>
                    </div>
                </c:otherwise>    
            </c:choose>
    </body>
</html>
