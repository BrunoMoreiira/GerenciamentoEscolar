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

public class ServletGerenciarComunicados extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String origin = request.getParameter("origin");
        Connection con = null;
        try {
            con = CriarConexao.getConexao();
            ComunicadosDAO comunicadosDAO = new ComunicadosDAO(con);
            if ("add".equals(action)) {
                Comunicados comunicado = new Comunicados();
                comunicado.setTexto_comunicado(request.getParameter("texto_comunicado"));
                comunicado.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
                comunicadosDAO.adicionarComunicado(comunicado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServletGerenciarComunicados.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // Redirecionar com base na origem
        if ("gerenciarComunicados".equals(origin)) {
            response.sendRedirect("gerenciarComunicados");
        } else if ("painelProfessor".equals(origin)) {
            request.getRequestDispatcher("sucesso.jsp").forward(request, response);
        } else {
            response.sendRedirect("comunicados"); // Redirecionamento padrão
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        try {
            con = CriarConexao.getConexao();
            ComunicadosDAO comunicadosDAO = new ComunicadosDAO(con);
            List<Comunicados> comunicados = comunicadosDAO.listarComunicados();
            request.setAttribute("comunicados", comunicados);
            request.getRequestDispatcher("/gerenciarComunicados.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletGerenciarComunicados.class.getName()).log(Level.SEVERE, null, ex);
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
