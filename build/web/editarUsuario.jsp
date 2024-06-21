<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Usuário</title>
    <link rel="stylesheet" type="text/css" href="style2.css">
</head>
<%
    int id = (int) request.getAttribute("Id");
    String nome = (String) request.getAttribute("Nome");
    String email = (String) request.getAttribute("Email");
    String senha = (String) request.getAttribute("Senha");
    String tipo = (String) request.getAttribute("Tipo");
%>
<body>
    <div class="container-user1">
        <h1>Editar Usuário</h1>
        <form action="update" method="post">
            <input type="hidden" name="id" value="<%= id %>">
            <div class="input-group">
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" value="<%= nome %>">
            </div>
            <div class="input-group">
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" value="<%= email %>">
            </div>
            <div class="input-group">
                <label for="senha">Senha:</label>
                <input type="password" id="senha" name="senha" value="<%= senha %>">
            </div>
            <div class="input-group">
                <label for="tipo">Tipo:</label>
                <input type="text" id="tipo" name="tipo" value="<%= tipo %>">
            </div>
            <input type="submit" value="Salvar Alterações" class="btn-item3">
        </form>
    </div>
</body>
</html>

