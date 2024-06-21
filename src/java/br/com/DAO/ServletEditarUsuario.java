package br.com.DAO;

import java.sql.Connection;
import br.com.conexao.CriarConexao;
import br.com.login.Usuario;
import br.com.login.UsuarioDAO;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.SQLException;

public class ServletEditarUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()){
            out.println("serverlt");
            int id = Integer.parseInt(request.getParameter("id"));
            Usuario u = new Usuario();
            try{
                Connection con = CriarConexao.getConexao();
                UsuarioDAO dao = new UsuarioDAO(con);
                u = dao.getUsuarioById(id);

                RequestDispatcher rd = 
                        request.getRequestDispatcher("editarUsuario.jsp");
                rd.forward(request, response);
            }
            catch(ServletException | IOException | SQLException e){

            }
            String msg = "";
            if (u == null) {
                msg = "Não foram encontrados registros.";
                request.setAttribute("msgResposta", msg);
                RequestDispatcher enviar = request.getRequestDispatcher("gerenciarUsuario.jsp");
                enviar.forward(request, response);
            } else {
                request.setAttribute("Id", u.getId());
                request.setAttribute("Nome", u.getNome());
                request.setAttribute("Email", u.getEmail());
                request.setAttribute("Senha", u.getSenha());
                request.setAttribute("Tipo", u.getTipo());
                RequestDispatcher lista = request.getRequestDispatcher("editarUsuario.jsp");
                lista.forward(request, response);
            }
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

