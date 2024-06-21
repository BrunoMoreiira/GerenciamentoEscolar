<%@page import="br.com.DAO.Disciplina"%>
<%@page import="br.com.DAO.DisciplinaDAO"%>
<%@page import="br.com.DAO.Comunicados"%>
<%@page import="br.com.DAO.ComunicadosDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.sql.SQLException" %>
<%@page import="br.com.login.Usuario" %>

<!DOCTYPE html>
<html>
<head>
    <title>Gerenciar Disciplinas</title>
    <link rel="stylesheet" type="text/css" href="style3.css">
</head>
<body>
    <div class="container">
        <h2>Adicionar Comunicado</h2>
        <form action="comunicados" method="post">
            <input type="hidden" name="origin" value="gerenciarComunicados">
            <input type="hidden" name="action" value="add">
            <label for="texto_comunicado">Digite o Comunicado:</label>
            <input type="text" id="texto_comunicado" name="texto_comunicado" required><br><br>
            <label for="id_usuario">Usuario:</label>
            <select id="id_usuario" name="id_usuario" required>
                <option value="">Selecione o Usuario</option>
                <% 
                    try {
                        DisciplinaDAO disciplinaDAO = new DisciplinaDAO(br.com.conexao.CriarConexao.getConexao());
                        ArrayList<Usuario> professores = disciplinaDAO.getUsuariosByTipo("professor");
                        ArrayList<Usuario> admins = disciplinaDAO.getUsuariosByTipo("admin");
                        for (Usuario professor : professores) {
                    %>
                        <option value="<%= professor.getId() %>"><%= professor.getNome() %></option>
                    <% 
                        }
                        for (Usuario admin : admins) {
                    %>
                        <option value="<%= admin.getId() %>"><%= admin.getNome() %></option>
                    <% 
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                %>

            </select><br><br>
            
            <input type="submit" value="Adicionar">
        </form>
            
        <h2>Comunicados</h2>
        <table>
    <tr>
        <th>ID</th>
        <th>Comunicado</th>
        <th>Data</th>
        <th>Usuário</th>
        <th>Ações</th>
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
        <td>
            <form action="alter1" method="get" style="display:inline;">
                <input type="hidden" name="id" value="<%= comunicado.getId_comunicados() %>">
                <button type="submit" class="edit-btn">Editar</button>
            </form>
            <form action="del1" method="post" style="display:inline;" onsubmit="return confirm('Tem certeza que deseja excluir este comunicado?')">
                <input type="hidden" name="id" value="<%= comunicado.getId_comunicados() %>">
                <button type="submit" class="delete-btn">Excluir</button>
            </form>
        </td>
    </tr>
    <% 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    %>
</table>

    </div>
</body>
</html> 