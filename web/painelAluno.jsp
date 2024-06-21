<%@page import="br.com.DAO.Disciplina"%>
<%@page import="br.com.DAO.DisciplinaDAO"%>
<%@page import="br.com.DAO.Comunicados"%>
<%@page import="br.com.DAO.ComunicadosDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.sql.SQLException" %>
<%@page import="br.com.login.Usuario" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Painel do Aluno</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="container">
        <h1>Bem-vindo ao Painel do Aluno</h1>
        <p>Aqui você pode visualizar suas disciplinas, comunicados e outras informações importantes.</p>
        <h2>Disciplinas</h2>
        <div class="button-container">
            <div class="button-link">Disciplina 1</div>
            <div class="button-link">Disciplina 2</div>
            <div class="button-link">Disciplina 3</div>
        </div>
        <h2>Comunicados</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Comunicado</th>
                <th>Data</th>
                <th>Usuário</th>
            </tr>
            <% 
                ComunicadosDAO comunicadosDAO = new ComunicadosDAO(br.com.conexao.CriarConexao.getConexao()); 
                try {
                    ArrayList<Comunicados> comunicados = comunicadosDAO.listarComunicados(); 
                    for (Comunicados comunicado : comunicados) { 
            %>
            <tr>
                <td><%= comunicado.getId_comunicados() %></td>
                <td><%= comunicado.getTexto_comunicado() %></td>
                <td><%= comunicado.getData_comunicado() %></td>
                <td><%= comunicado.getNome_usuario() %></td>
            </tr>
            <% 
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            %>
        </table>
        <div class="button-container">
            <a href="remover.jsp" class="button-link">Sair</a>
        </div>
    </div>
</body>
</html>
