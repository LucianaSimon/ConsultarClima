<%-- 
    Document   : temperaturasPor Provincias
    Created on : Dec 9, 2020, 1:26:23 PM
    Author     : Luciana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Temperatura Por Localiad</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                $.getJSON("http://localhost:8084/Clima/Provincias", function (result) {
                    $.each(result, function (i, nombre) {
                        $("#Provincia").append("<option value='" + nombre + "'>" + nombre + "</option>");
                        console.log(nombre);
                    });
                });
                
                
                $("#Provincia").change(function () {

                    $.ajax({
                        url: 'getTemperaturasPorProvincias.jsp?provincia=' + $("#Provincia").val(),
                        type: 'GET',
                        
                        success: function (data) {
                            //alert(data);
                            $("#tabla").html(data);
                            console.log(data);
                        },
                        error: function () {
                            alert("error");
                            console.log('Error in Operation');
                        }
                    });
                });
                
                ws = new WebSocket("ws://localhost:8084/Clima/endpoint");
                ws.onmessage = function(evt){
                    var cant = evt.data;
                    document.getElementById("#cantUsuarios").innerHTML = "Usuarios en esta pagina: " + cant;
                    
                    if(cant> 2){
                        $("#ocultar").hide();
                    } else {
                        $("#ocultar").show();
                    }
                }
            });      
            
        </script>
    </head>
    <body>
        <h1 style="color:pink">Temperaturas Por Localidad</h1>
        <div id ="ocultar">
        <label>Provincia:</label> <select id="Provincia"></select>
        <div id="tabla"></div>
        <p id="cantUsuarios"></p></div>
        <a href="index.html" style="text-align:center">Home</a>
    </body>
</html>
