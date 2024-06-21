<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Disciplina</title>
    <link rel="stylesheet" type="text/css" href="style2.css">
</head>
<%
    int id = (int) request.getAttribute("id");
    String nome = request.getAttribute("nome") != null ? (String) request.getAttribute("nome") : "";
    String descricao = request.getAttribute("descricao") != null ? (String) request.getAttribute("descricao") : "";
    int id_professor = (int) request.getAttribute("id_professor");
    int id_aluno = (int) request.getAttribute("id_aluno");
%>
<body>
    <div class="container-user1">
        <h1>Editar Disciplina</h1>
        <form action="alterar" method="post">
            <input type="hidden" name="id" value="<%= id %>">
            <div class="input-group">
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" value="<%= nome %>">
            </div>
            <div class="input-group">
                <label for="descricao">Descrição:</label>
                <input type="text" id="descricao" name="descricao" value="<%= descricao %>">
            </div>
            <div class="input-group">
                <label for="id_professor">ID Professor:</label>
                <input type="text" id="id_professor" name="id_professor" value="<%= id_professor %>">
            </div>
            <div class="input-group">
                <label for="id_aluno">ID Aluno:</label>
                <input type="text" id="id_aluno" name="id_aluno" value="<%= id_aluno %>">
            </div>
            <input type="submit" value="Salvar Alterações" class="btn-item3">
        </form>
    </div>
</body>
</html>