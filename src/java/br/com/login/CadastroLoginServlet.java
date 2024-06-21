package br.com.login;

import br.com.conexao.CriarConexao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CadastroLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CadastroLoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String tipo = request.getParameter("tipo");

        Connection con;
        try {
            con = CriarConexao.getConexao();

            Usuario u = new Usuario();
            u.setNome(nome);
            u.setEmail(email);
            u.setSenha(senha);
            u.setTipo(tipo);
            
            UsuarioDAO dao = new UsuarioDAO(con);
            dao.adicionar(u);

            request.setAttribute("nome", u.getNome());
            request.setAttribute("email", u.getEmail());
            request.setAttribute("tipo", u.getTipo());

            request.getRequestDispatcher("cadastrado.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
