<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Comunicado</title>
    <link rel="stylesheet" type="text/css" href="style2.css">
</head>
<%
    String idComunicadosParam = request.getAttribute("id_comunicados") != null ? request.getAttribute("id_comunicados").toString() : "0";
    String textoComunicado = request.getAttribute("texto_comunicado") != null ? request.getAttribute("texto_comunicado").toString() : "";
    String dataComunicado = request.getAttribute("data_comunicado") != null ? request.getAttribute("data_comunicado").toString() : "";
    String idUsuarioParam = request.getAttribute("id_usuario") != null ? request.getAttribute("id_usuario").toString() : "0";

    int idComunicados = Integer.parseInt(idComunicadosParam);
    int idUsuario = Integer.parseInt(idUsuarioParam);
%>
<body>
    <div class="container-user1">
        <h1>Editar Comunicado</h1>
        <form action="update1" method="post">
            <input type="hidden" name="id_comunicados" value="<%= idComunicados %>">
            <div class="input-group">
                <label for="textoComunicado">Comunicado:</label>
                <input type="text" id="textoComunicado" name="texto_comunicado" value="<%= textoComunicado %>">
            </div>
            <div class="input-group">
                <label for="dataComunicado">Data:</label>
                <input type="text" id="dataComunicado" name="data_comunicado" value="<%= dataComunicado %>" disabled="true">
            </div>
            <div class="input-group">
                <label for="idUsuario">ID Usuário:</label>
                <input type="text" id="idUsuario" name="id_usuario" value="<%= idUsuario %>">
            </div>
            <input type="submit" value="Salvar Alterações" class="btn-item3">
        </form>
    </div>
</body>
</html>
