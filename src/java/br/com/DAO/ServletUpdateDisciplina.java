package br.com.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import br.com.conexao.CriarConexao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/atualizar")
public class ServletUpdateDisciplina extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8"); // Ensure request encoding is set to UTF-8
        
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String descricao = request.getParameter("descricao");
            String nota = request.getParameter("nota");
            int id_professor = Integer.parseInt(request.getParameter("id_professor"));
            int id_aluno = Integer.parseInt(request.getParameter("id_aluno"));

            Disciplina disciplina = new Disciplina();
            disciplina.setId_disciplina(id);
            disciplina.setNome(nome);
            disciplina.setDescricao(descricao);
            disciplina.setNota(nota);
            disciplina.setId_professor(id_professor);
            disciplina.setId_aluno(id_aluno);

            try {
                Connection con = CriarConexao.getConexao();
                DisciplinaDAO dao = new DisciplinaDAO(con);
                dao.alterar(disciplina);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            response.sendRedirect("disciplinas");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("editarDisciplina.jsp?id=" + request.getParameter("id") + "&error=true");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para atualizar uma disciplina existente";
    }
}
