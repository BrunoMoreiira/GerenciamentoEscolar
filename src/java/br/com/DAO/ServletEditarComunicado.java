package br.com.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.com.conexao.CriarConexao;

@WebServlet("/editarComunicado")
public class ServletEditarComunicado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Connection con = CriarConexao.getConexao();
            ComunicadosDAO dao = new ComunicadosDAO(con);
            Comunicados c = dao.getComunicadoById(id);

            if (c == null) {
                String msg = "Não foram encontrados registros.";
                request.setAttribute("msgResposta", msg);
                RequestDispatcher enviar = request.getRequestDispatcher("gerenciarComunicados.jsp");
                enviar.forward(request, response);
            } else {
                request.setAttribute("id_comunicados", c.getId_comunicados());
                request.setAttribute("texto_comunicado", c.getTexto_comunicado());
                request.setAttribute("data_comunicado", c.getData_comunicado());
                request.setAttribute("id_usuario", c.getId_usuario());
                RequestDispatcher lista = request.getRequestDispatcher("editarComunicados.jsp");
                lista.forward(request, response);
            }

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetro inválido ou erro de banco de dados.");
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
        return "Servlet para editar comunicados";
    }
}
