<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Style/galeria.css"/>
        <title>Błąd!</title>
    </head>
    <body>
        <jsp:useBean class="emotki.Kontroler" id="kontroler" scope="session"/>
        <h1>Wystąpił błąd!</h1>
        <div class="form_cont">
            ${kontroler.status}
            <br/><br/>
            <form action="galeria.jsp" method="post" style="text-align: center">
                <input type="submit" value="Od początku" class="button"/>
            </form>
        </div>
    </body>
</html>
