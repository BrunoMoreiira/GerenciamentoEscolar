<%@page import="br.com.login.Usuario"%>
<%@page import="br.com.login.UsuarioDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuários</title>
    <link rel="stylesheet" type="text/css" href="style2.css">
</head>
<body>
    <div class="container-user">
        <h1>Lista de Usuários</h1>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Nome</th>
                    <th scope="col">Email</th>
                    <th scope="col">Senha</th>
                    <th scope="col">Tipo</th>
                    <th scope="col">Ações</th>
                </tr>
            </thead>
            <tbody>
                <% 
                try {
                    UsuarioDAO usuarioDAO = new UsuarioDAO(br.com.conexao.CriarConexao.getConexao());
                    List<Usuario> usuarios = usuarioDAO.getUsuarios();
                    for (Usuario usuario : usuarios) { 
                %>
                <tr>
                    <td><%= usuario.getId() %></td>
                    <td><%= usuario.getNome() %></td>
                    <td><%= usuario.getEmail() %></td>
                    <td><%= usuario.getSenha() %></td>
                    <td><%= usuario.getTipo() %></td>
                    <td>
                        <a href="alter?id=<%= usuario.getId() %>" ><div class="btn-item">Editar</div></a>
                        <a href="del?id=<%= usuario.getId() %>" class="btn-delete" onclick="return confirm('Tem certeza que deseja excluir <%= usuario.getNome() %> ?')"><div class="btn-item2">Excluir</div></a>
                    </td>
                </tr>
                <% 
                    }
                } catch (Exception e) {
                    out.println("Erro ao buscar usuários: " + e.getMessage());
                }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
