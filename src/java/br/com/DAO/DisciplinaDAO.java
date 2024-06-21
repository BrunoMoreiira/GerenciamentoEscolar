package br.com.DAO;

import br.com.login.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DisciplinaDAO {

    private final Connection con;

    public DisciplinaDAO(Connection con) throws SQLException {
        this.con = con;
    }


    public ArrayList<Disciplina> getDisciplinas() throws SQLException {
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT d.*, "
                   + "p.nome AS professor_nome, "
                   + "a.nome AS aluno_nome "
                   + "FROM disciplina d "
                   + "LEFT JOIN usuario p ON d.id_professor = p.id_usuario "
                   + "LEFT JOIN usuario a ON d.id_aluno = a.id_usuario";
        try (PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setId_disciplina(rs.getInt("id_disciplina"));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setDescricao(rs.getString("descricao"));
                disciplina.setNota(rs.getString("nota"));
                disciplina.setId_aluno(rs.getInt("id_aluno"));
                disciplina.setId_professor(rs.getInt("id_professor"));
                disciplina.setNomeProfessor(rs.getString("professor_nome"));
                disciplina.setNomeAluno(rs.getString("aluno_nome"));
                disciplinas.add(disciplina);
            }
        }
        return disciplinas;
    }
    
    
    public ArrayList<Usuario> getUsuariosByTipo(String tipo) throws SQLException {
    ArrayList<Usuario> usuarios = new ArrayList<>();
    String sql = "SELECT * FROM usuario WHERE tipo = ?";
    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, tipo);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuarios.add(usuario);
            }
        }
    }
    return usuarios;
}


    public void inserir(Disciplina disciplina) throws SQLException {
        String sql = "INSERT INTO disciplina (nome, descricao, nota, id_aluno, id_professor) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, disciplina.getNome());
            stmt.setString(2, disciplina.getDescricao());
            stmt.setString(3, disciplina.getNota()); // Changed to setString
            stmt.setInt(4, disciplina.getId_aluno());
            stmt.setInt(5, disciplina.getId_professor());
            stmt.execute();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM disciplina WHERE id_disciplina = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        }
    }

    public void alterar(Disciplina disciplina) throws SQLException {
        String sql = "UPDATE disciplina SET nome = ?, descricao = ?, nota = ?, id_aluno = ?, id_professor = ? WHERE id_disciplina = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, disciplina.getNome());
            stmt.setString(2, disciplina.getDescricao());
            stmt.setString(3, disciplina.getNota()); // Changed to setString
            stmt.setInt(4, disciplina.getId_aluno());
            stmt.setInt(5, disciplina.getId_professor());
            stmt.setInt(6, disciplina.getId_disciplina());
            stmt.execute();
        }
    }
    

    public Disciplina getDisciplinaById(int id) throws SQLException {
    Disciplina dc = null;
    String sql = "SELECT * FROM disciplina WHERE id_disciplina = ?";
    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                dc = new Disciplina();
                dc.setId_disciplina(rs.getInt("id_disciplina"));
                dc.setNome(rs.getString("nome"));
                dc.setDescricao(rs.getString("descricao"));
                dc.setNota(rs.getString("nota"));
                dc.setId_professor(rs.getInt("id_professor"));
                dc.setId_aluno(rs.getInt("id_aluno"));
            }
        }
    }
    return dc;
}

}