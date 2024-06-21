    package br.com.login;

    import br.com.conexao.CriarConexao;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;

    public class UsuarioDAO extends CriarConexao{

    private final Connection con;

        public UsuarioDAO(Connection con) throws SQLException {
            this.con = con;
        }

        public void adicionar(Usuario u) throws SQLException {
            String sql = "INSERT INTO usuario (nome, email, senha, tipo) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, u.getNome());
                stmt.setString(2, u.getEmail());
                stmt.setString(3, u.getSenha());
                stmt.setString(4, u.getTipo());
                stmt.execute();
            }
        }

        public List<Usuario> getUsuarios() throws SQLException {
            List<Usuario> usuarios = new ArrayList<>();
            String sql = "SELECT * FROM usuario";
            try (PreparedStatement stmt = con.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id_usuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setTipo(rs.getString("tipo"));
                    usuarios.add(usuario);
                }
            }
            return usuarios;
        }

        public Usuario getUsuarioById(int id) throws SQLException {
            Usuario us = null;
            String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        us = new Usuario();
                        us.setId(rs.getInt("id_usuario"));
                        us.setNome(rs.getString("nome"));
                        us.setEmail(rs.getString("email"));
                        us.setSenha(rs.getString("senha"));
                        us.setTipo(rs.getString("tipo"));
                    }
                }
            }
            return us;
        }

        public void updateUsuario(Usuario u) throws SQLException {
            String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, tipo = ? WHERE id_usuario = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, u.getNome());
                stmt.setString(2, u.getEmail());
                stmt.setString(3, u.getSenha());
                stmt.setString(4, u.getTipo());
                stmt.setInt(5, u.getId());
                stmt.executeUpdate();
            }
        }

        public void deleteUsuario(int id) throws SQLException {
            String sql = "DELETE FROM usuario WHERE id_usuario = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        }
    }
