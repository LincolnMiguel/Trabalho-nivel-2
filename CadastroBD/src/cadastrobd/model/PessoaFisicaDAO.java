package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {
    public PessoaFisica getPessoa(int id) {
        PessoaFisica pessoaFisica = null;
        String sql = "SELECT p.id, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, p.cpf FROM Pessoa p WHERE p.id = ?";

        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                pessoaFisica = new PessoaFisica(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("logradouro"),
                    resultSet.getString("cidade"),
                    resultSet.getString("estado"),
                    resultSet.getString("telefone"),
                    resultSet.getString("email"),
                    resultSet.getString("cpf")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoaFisica;
    }

    public List<PessoaFisica> getPessoas() {
        List<PessoaFisica> pessoas = new ArrayList<>();
        String sql = "SELECT p.id, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pf.cpf FROM Pessoa p JOIN PessoaFisica pf ON p.id = pf.idPessoaFisica";

        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                
                PessoaFisica pessoaFisica = new PessoaFisica(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("logradouro"),
                    resultSet.getString("cidade"),
                    resultSet.getString("estado"),
                    resultSet.getString("telefone"),
                    resultSet.getString("email"),
                    resultSet.getString("cpf")
                );
                pessoas.add(pessoaFisica);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    public void incluir(PessoaFisica pessoaFisica) {
        String sqlPessoa = "INSERT INTO Pessoa (nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlPessoaFisica = "INSERT INTO PessoaFisica (idPessoaFisica, cpf) VALUES (?, ?)";

        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatementPessoa = connection.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement preparedStatementPessoaFisica = connection.prepareStatement(sqlPessoaFisica)) {

            preparedStatementPessoa.setString(1, pessoaFisica.getNome());
            preparedStatementPessoa.setString(2, pessoaFisica.getLogradouro());
            preparedStatementPessoa.setString(3, pessoaFisica.getCidade());
            preparedStatementPessoa.setString(4, pessoaFisica.getEstado());
            preparedStatementPessoa.setString(5, pessoaFisica.getTelefone());
            preparedStatementPessoa.setString(6, pessoaFisica.getEmail());
            preparedStatementPessoa.executeUpdate();

            ResultSet generatedKeys = preparedStatementPessoa.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idGerado = generatedKeys.getInt(1);

                preparedStatementPessoaFisica.setInt(1, idGerado);
                preparedStatementPessoaFisica.setString(2, pessoaFisica.getCpf());
                preparedStatementPessoaFisica.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaFisica pessoaFisica) {
        String sqlPessoa = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE id = ?";
        String sqlPessoaFisica = "UPDATE PessoaFisica SET cpf = ? WHERE idPessoaFisica = ?";

        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatementPessoa = connection.prepareStatement(sqlPessoa);
             PreparedStatement preparedStatementPessoaFisica = connection.prepareStatement(sqlPessoaFisica)) {

            preparedStatementPessoa.setString(1, pessoaFisica.getNome());
            preparedStatementPessoa.setString(2, pessoaFisica.getLogradouro());
            preparedStatementPessoa.setString(3, pessoaFisica.getCidade());
            preparedStatementPessoa.setString(4, pessoaFisica.getEstado());
            preparedStatementPessoa.setString(5, pessoaFisica.getTelefone());
            preparedStatementPessoa.setString(6, pessoaFisica.getEmail());
            preparedStatementPessoa.setInt(7, pessoaFisica.getId());
            preparedStatementPessoa.executeUpdate();

            preparedStatementPessoaFisica.setString(1, pessoaFisica.getCpf());
            preparedStatementPessoaFisica.setInt(2, pessoaFisica.getId());
            preparedStatementPessoaFisica.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sqlPessoaFisica = "DELETE FROM PessoaFisica WHERE idPessoaFisica = ?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE id = ?";

        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatementPessoaFisica = connection.prepareStatement(sqlPessoaFisica);
             PreparedStatement preparedStatementPessoa = connection.prepareStatement(sqlPessoa)) {

            preparedStatementPessoaFisica.setInt(1, id);
            preparedStatementPessoaFisica.executeUpdate();

            preparedStatementPessoa.setInt(1, id);
            preparedStatementPessoa.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


