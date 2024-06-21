
package br.com.DAO;


import br.com.login.Usuario;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class ComunicadosDAO {
    
    private final Connection con;

    public ComunicadosDAO(Connection connection) {
        this.con = connection;
    }

    public void adicionarComunicado(Comunicados comunicado) throws SQLException {
       String sql = "INSERT INTO Comunicados (texto_comunicado, data_comunicado, id_usuario) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, comunicado.getTexto_comunicado());
            stmt.setDate(2, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
            stmt.setInt(3, comunicado.getId_usuario());
            stmt.execute();
        }
    }

    public void editarComunicado(Comunicados comunicado) throws SQLException {
    String sql = "UPDATE Comunicados SET texto_comunicado = ?, id_usuario = ? WHERE id_comunicados = ?;";
    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, comunicado.getTexto_comunicado());
        stmt.setInt(2, comunicado.getId_usuario());
        stmt.setInt(3, comunicado.getId_comunicados());
        stmt.execute();
    }
}


    public void excluirComunicado(int id_comunicados) throws SQLException {
        String sql = "DELETE FROM Comunicados WHERE id_comunicados = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id_comunicados);
            stmt.execute();
        }
    }

    public ArrayList<Comunicados> listarComunicados() throws SQLException {
    ArrayList<Comunicados> comunicados = new ArrayList<>();
    String query = "SELECT c.*, u.nome FROM comunicados c " +
                   "JOIN usuario u ON c.id_usuario = u.id_usuario";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comunicados comunicado = new Comunicados();
                comunicado.setId_comunicados(resultSet.getInt("id_comunicados"));
                comunicado.setTexto_comunicado(resultSet.getString("texto_comunicado"));
                comunicado.setData_comunicado(resultSet.getString("data_comunicado"));
                comunicado.setId_usuario(resultSet.getInt("id_usuario"));
                comunicado.setNome_usuario(resultSet.getString("nome"));

                comunicados.add(comunicado);
            }
        }

        return comunicados;
    }

    public Comunicados getComunicadoById(int id) {
        Comunicados comunicado = null;
        String sql = "SELECT c.*, u.nome_usuario FROM comunicados c " +
                     "JOIN usuarios u ON c.id_usuario = u.id_usuario WHERE c.id_comunicados = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    comunicado = new Comunicados();
                    comunicado.setId_comunicados(rs.getInt("id_comunicados"));
                    comunicado.setTexto_comunicado(rs.getString("texto_comunicado"));
                    comunicado.setData_comunicado(rs.getString("data_comunicado"));
                    comunicado.setId_usuario(rs.getInt("id_usuario"));
                    comunicado.setNome_usuario(rs.getString("nome_usuario"));
                }
            }
        } catch (SQLException ex) {
            // tratar exceção aqui
        }
        return comunicado;
}

    
    
}
