<%@page import="br.com.DAO.Disciplina"%>
<%@page import="br.com.DAO.DisciplinaDAO"%>
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
        <h2>Adicionar Disciplina</h2>
        <form action="disciplinas" method="post">
            <input type="hidden" name="action" value="add">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" required><br><br>
            <label for="descricao">Descrição:</label>
            <textarea id="descricao" name="descricao" required></textarea><br><br>
            <label for="professor">Professor:</label>
            <select id="professor" name="professor" required>
                <option value="">Selecione um Professor</option>
                <% 
                    try {
                        DisciplinaDAO disciplinaDAO = new DisciplinaDAO(br.com.conexao.CriarConexao.getConexao());
                        ArrayList<Usuario> professores = disciplinaDAO.getUsuariosByTipo("professor");
                        for (Usuario professor : professores) {
                %>
                    <option value="<%= professor.getId() %>"><%= professor.getNome() %></option>
                <% 
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                %>
            </select><br><br>
            <label for="aluno">Aluno:</label>
            <select id="aluno" name="aluno" required>
                <option value="">Selecione um Aluno</option>
                <% 
                    try {
                        DisciplinaDAO disciplinaDAO = new DisciplinaDAO(br.com.conexao.CriarConexao.getConexao());
                        ArrayList<Usuario> alunos = disciplinaDAO.getUsuariosByTipo("aluno");
                        for (Usuario aluno : alunos) {
                %>
                    <option value="<%= aluno.getId() %>"><%= aluno.getNome() %></option>
                <% 
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                %>
            </select><br><br>
            <input type="submit" value="Adicionar">
        </form>
            
        <h2>Disciplinas</h2>
        <table>
           <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Descrição</th>
        <th>Professor</th>
        <th>Aluno</th>
        <th>Ações</th>
    </tr>
    <% 
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO(br.com.conexao.CriarConexao.getConexao()); // Instanciando o DAO
        try {
            ArrayList<Disciplina> disciplinas = disciplinaDAO.getDisciplinas(); // Obtendo todas as disciplinas
            for (Disciplina disciplina : disciplinas) { // Iterando sobre cada disciplina
    %>
        <tr>
            <td><%= disciplina.getId_disciplina() %></td>
            <td><%= disciplina.getNome() %></td>
            <td><%= disciplina.getDescricao() %></td>
            <td><%= disciplina.getNomeProfessor() %></td>
            <td><%= disciplina.getNomeAluno() %></td>
            <td>
                <form action="editar" method="get" style="display:inline;">
                    <input type="hidden" name="id" value="<%= disciplina.getId_disciplina() %>">
                    <button type="submit" class="edit-btn btn-item">Editar</button> <!-- Adicionando a classe edit-btn -->
                </form>
                <form action="excluir" method="get" style="display:inline;" onsubmit="return confirm('Tem certeza que deseja excluir <%= disciplina.getNome() %> ?')">
                    <input type="hidden" name="id" value="<%= disciplina.getId_disciplina() %>">
                    <button type="submit" class="delete-btn btn-item2">Excluir</button> <!-- Adicionando a classe delete-btn -->
                </form>
            </td>

        </tr>
    <% 
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Tratamento de exceção em caso de erro ao recuperar as disciplinas do banco de dados
        }
    %>

        </table>
    </div>
</body>
</html> 