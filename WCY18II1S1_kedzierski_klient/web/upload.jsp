<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c"  uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="UTF-8">
        <link rel="stylesheet" href="Style/galeria.css"/>
        <title>Dodaj emotikonę</title>
    </head>
    <body>
        <h1>Dodawanie emotikony</h1>
        <div class="form_cont">
            <form action="UploadAction" method="post" enctype="multipart/form-data">
                
                Plik graficzny:<br/>
                <button class="button" disabled="true" style="padding: 0; overflow: hidden; width: 280px">
                    <!--<button disabled="true" class="button">Wybierz plik</button>-->
                    <input 
                    style="transform: translate(-165px, -3px); font-size: 22pt; padding: 8px 0;"
                    required
                    id="file"
                    type="file" 
                    accept="image/png" 
                    name="file" 
                    multiple="false"/>
                </button><br/><br/>
                
                Krótki opis:<br/>
                <textarea class="text_input" name="desc" rows="2" required ></textarea><br/><br/>
                
                <input class="button" type="submit" value="Dodaj!"><br/>         
            </form>
        </div>
    </body>
</html>
