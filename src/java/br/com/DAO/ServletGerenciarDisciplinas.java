package br.com.DAO;

import br.com.conexao.CriarConexao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletGerenciarDisciplinas extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Connection con = null;
        try {
            con = CriarConexao.getConexao();
            DisciplinaDAO disciplinaDAO = new DisciplinaDAO(con);
            if ("add".equals(action)) {
                Disciplina disciplina = new Disciplina();
                disciplina.setNome(request.getParameter("nome"));
                disciplina.setDescricao(request.getParameter("descricao"));
                disciplina.setNota(request.getParameter("nota")); // Set nota as String
                disciplina.setId_professor(Integer.parseInt(request.getParameter("professor")));
                disciplina.setId_aluno(Integer.parseInt(request.getParameter("aluno")));
                disciplinaDAO.inserir(disciplina);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServletGerenciarDisciplinas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        response.sendRedirect("disciplinas");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        try {
            con = CriarConexao.getConexao();
            DisciplinaDAO disciplinaDAO = new DisciplinaDAO(con);
            List<Disciplina> disciplinas = disciplinaDAO.getDisciplinas();
            request.setAttribute("disciplinas", disciplinas);
            request.getRequestDispatcher("/gerenciarDisciplina.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletGerenciarDisciplinas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    
    
}