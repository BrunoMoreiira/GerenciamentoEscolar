<%@page import="br.com.login.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.sql.SQLException" %>
<%@page import="jakarta.servlet.http.HttpSession"%> <!-- Corrigir a importação -->
<html>
<head>
    <meta charset="UTF-8">
    <title>Painel do Professor</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="container">
        <h1>Bem-vindo ao Painel do Professor</h1>
        <p>Aqui você pode gerenciar suas disciplinas, enviar comunicados e outras atividades relacionadas.</p>
        <h2>Minhas Disciplinas</h2>
        <div class="button-container">
            <div class="button-link">Disciplina 1</div>
            <div class="button-link">Disciplina 2</div>
            <div class="button-link">Disciplina 3</div>
        </div>
        <h2>Enviar Comunicado</h2>
        <form action="comunicados" method="post">
            <input type="hidden" name="origin" value="painelProfessor">
            <input type="hidden" name="action" value="add">
            <label for="texto_comunicado">Digite o Comunicado:</label>
            <input type="text" id="texto_comunicado" name="texto_comunicado" required><br><br>
            <label for="id_usuario">Usuário:</label>
            <select id="id_usuario" name="id_usuario" required>
                <% 
                    // HttpSession session = request.getSession(); -- Variável já está disponível no JSP
                    Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
                    if (usuarioLogado != null) {
                %>
                    <option value="<%= usuarioLogado.getId() %>"><%= usuarioLogado.getNome() %></option>
                <% 
                    }
                %>
            </select><br><br>
            <input type="submit" value="Enviar Comunicado">
        </form>
        <div class="button-container">
            <a href="remover.jsp" class="button-link">Sair</a>
        </div>
    </div>
</body>
</html>
