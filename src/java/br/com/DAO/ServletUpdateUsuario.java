package br.com.DAO;

import java.sql.Connection;
import br.com.conexao.CriarConexao;
import br.com.login.Usuario;
import br.com.login.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletUpdateUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int id;
            String nome;
            String email;
            String senha;
            String tipo;

            id = Integer.parseInt(request.getParameter("id"));
            nome = request.getParameter("nome");
            email = request.getParameter("email");
            senha = request.getParameter("senha");
            tipo = request.getParameter("tipo");

            try {
                Connection con = CriarConexao.getConexao();
                UsuarioDAO dao = new UsuarioDAO(con);
                Usuario usuario = new Usuario();
                usuario.setId(id);
                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setSenha(senha);
                usuario.setTipo(tipo);
                
                dao.updateUsuario(usuario);
                
                request.setAttribute("mensagem", "Usuário atualizado com sucesso!");
                RequestDispatcher rd = request.getRequestDispatcher("gerenciarUsuario.jsp");
                rd.forward(request, response);
            } catch (Exception e) {

                out.println(e);
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
