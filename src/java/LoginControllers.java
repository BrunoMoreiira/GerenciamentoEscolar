import br.com.conexao.CriarConexao;
import br.com.login.Usuario;
import br.com.login.UsuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class LoginControllers extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        Usuario usuario = null;
        Connection con = null;
        
        try {
            con = CriarConexao.getConexao();
            String sql = "SELECT id_usuario, email, senha, tipo, nome FROM usuario WHERE email = ? AND senha = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getString("tipo"));
                usuario.setNome(rs.getString("nome"));
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", usuario);
            
            switch (usuario.getTipo()) {
                case "admin":
                    request.getRequestDispatcher("painelAdmin.jsp").forward(request, response);
                    break;
                case "aluno":
                    request.getRequestDispatcher("painelAluno.jsp").forward(request, response);
                    break;
                case "professor":
                    request.getRequestDispatcher("painelProfessor.jsp").forward(request, response);
                    break;
                default:
                    request.getRequestDispatcher("errodeusuario.jsp").forward(request, response);
                    break;
            }
        } else {
            request.getRequestDispatcher("errodeusuario.jsp").forward(request, response);
        }
    }
}
