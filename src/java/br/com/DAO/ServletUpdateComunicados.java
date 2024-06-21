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

@WebServlet("/update1")
public class ServletUpdateComunicados extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8"); // Ensure request encoding is set to UTF-8
        
        try {
            int id = Integer.parseInt(request.getParameter("id_comunicados"));
            String textoComunicado = request.getParameter("texto_comunicado");
            int idUsuario = Integer.parseInt(request.getParameter("id_usuario"));

            Comunicados comunicado = new Comunicados();
            comunicado.setId_comunicados(id);
            comunicado.setTexto_comunicado(textoComunicado);
            comunicado.setId_usuario(idUsuario);

            try {
                Connection con = CriarConexao.getConexao();
                ComunicadosDAO dao = new ComunicadosDAO(con);
                dao.editarComunicado(comunicado);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            response.sendRedirect("comunicados");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("editarComunicados.jsp?id_comunicados=" + request.getParameter("id_comunicados") + "&error=true");
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
        return "Servlet para atualizar um comunicado existente";
    }
}
