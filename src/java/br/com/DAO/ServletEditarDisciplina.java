package br.com.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import br.com.conexao.CriarConexao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ServletEditarDisciplina extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()) {
            out.println("serverlt");
            int id = Integer.parseInt(request.getParameter("id"));
            Disciplina d = new Disciplina();
            try{
                Connection con = CriarConexao.getConexao();
                DisciplinaDAO dao = new DisciplinaDAO(con);
                d = dao.getDisciplinaById(id);

                RequestDispatcher rd = 
                        request.getRequestDispatcher("editarDisciplina.jsp");
                rd.forward(request, response);
            }
            catch(ServletException | IOException | SQLException e){

            }
            String msg = "";
            if (d == null) {
                msg = "Não foi encontrada uma disciplina com o ID fornecido.";
                request.setAttribute("msgResposta", msg);
                RequestDispatcher enviar = request.getRequestDispatcher("editarDisciplina.jsp");
                enviar.forward(request, response);
            } else {
                request.setAttribute("id", d.getId_disciplina());
                request.setAttribute("nome", d.getNome());
                request.setAttribute("descricao", d.getDescricao());
                request.setAttribute("nota", d.getNota());
                request.setAttribute("id_professor", d.getId_professor());
                request.setAttribute("id_aluno", d.getId_aluno());
                RequestDispatcher lista = request.getRequestDispatcher("editarDisciplina.jsp");
                lista.forward(request, response);
            }
            System.out.println("ID da disciplina: " + d.getId_disciplina());
            System.out.println("Nome da disciplina: " + d.getNome());

        } catch (NumberFormatException | ServletException | IOException  e) {
            // Trate exceções adequadamente
            e.printStackTrace();
            String msg = "Ocorreu um erro ao processar a solicitação.";
            request.setAttribute("msgResposta", msg);
            RequestDispatcher enviar = request.getRequestDispatcher("gerenciarDisciplina.jsp");
            enviar.forward(request, response);
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
        return "Short description";
    }
}
