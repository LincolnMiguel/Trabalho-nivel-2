package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {
    public PessoaJuridica getPessoa(int id) {
        PessoaJuridica pessoaJuridica = null;
        String sql = "SELECT p.id, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pj.cnpj FROM Pessoa p JOIN PessoaJuridica pj ON p.id = pj.id WHERE p.id = ?";

        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                pessoaJuridica = new PessoaJuridica(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("logradouro"),
                    resultSet.getString("cidade"),
                    resultSet.getString("estado"),
                    resultSet.getString("telefone"),
                    resultSet.getString("email"),
                    resultSet.getString("cnpj")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoaJuridica;
    }

    public List<PessoaJuridica> getPessoas() {
        List<PessoaJuridica> pessoas = new ArrayList<>();
        String sql = "SELECT p.id, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pj.cnpj FROM Pessoa p JOIN PessoaJuridica pj ON p.id = pj.idPessoaJuridica";

        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                PessoaJuridica pessoaJuridica = new PessoaJuridica(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("logradouro"),
                    resultSet.getString("cidade"),
                    resultSet.getString("estado"),
                    resultSet.getString("telefone"),
                    resultSet.getString("email"),
                    resultSet.getString("cnpj")
                );
                pessoas.add(pessoaJuridica);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    public void incluir(PessoaJuridica pessoaJuridica) {
        String sqlPessoa = "INSERT INTO Pessoa (nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlPessoaJuridica = "INSERT INTO PessoaJuridica (idPessoaJuridica, cnpj) VALUES (?, ?)";

        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatementPessoa = connection.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement preparedStatementPessoaJuridica = connection.prepareStatement(sqlPessoaJuridica)) {

            // Inserir na tabela Pessoa
            preparedStatementPessoa.setString(1, pessoaJuridica.getNome());
            preparedStatementPessoa.setString(2, pessoaJuridica.getLogradouro());
            preparedStatementPessoa.setString(3, pessoaJuridica.getCidade());
            preparedStatementPessoa.setString(4, pessoaJuridica.getEstado());
            preparedStatementPessoa.setString(5, pessoaJuridica.getTelefone());
            preparedStatementPessoa.setString(6, pessoaJuridica.getEmail());
            preparedStatementPessoa.executeUpdate();

            ResultSet generatedKeys = preparedStatementPessoa.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idGerado = generatedKeys.getInt(1);
                
                preparedStatementPessoaJuridica.setInt(1, idGerado);
                preparedStatementPessoaJuridica.setString(2, pessoaJuridica.getCnpj());
                preparedStatementPessoaJuridica.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaJuridica pessoaJuridica) {
        String sqlPessoa = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE id = ?";
        String sqlPessoaJuridica = "UPDATE PessoaJuridica SET cnpj = ? WHERE idPessoaJuridica = ?";

        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatementPessoa = connection.prepareStatement(sqlPessoa);
             PreparedStatement preparedStatementPessoaJuridica = connection.prepareStatement(sqlPessoaJuridica)) {

            preparedStatementPessoa.setString(1, pessoaJuridica.getNome());
            preparedStatementPessoa.setString(2, pessoaJuridica.getLogradouro());
            preparedStatementPessoa.setString(3, pessoaJuridica.getCidade());
            preparedStatementPessoa.setString(4, pessoaJuridica.getEstado());
            preparedStatementPessoa.setString(5, pessoaJuridica.getTelefone());
            preparedStatementPessoa.setString(6, pessoaJuridica.getEmail());
            preparedStatementPessoa.setInt(7, pessoaJuridica.getId());
            preparedStatementPessoa.executeUpdate();

            preparedStatementPessoaJuridica.setString(1, pessoaJuridica.getCnpj());
            preparedStatementPessoaJuridica.setInt(2, pessoaJuridica.getId());
            preparedStatementPessoaJuridica.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sqlPessoaJuridica = "DELETE FROM PessoaJuridica WHERE idPessoaJuridica = ?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE id = ?";

        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatementPessoaJuridica = connection.prepareStatement(sqlPessoaJuridica);
             PreparedStatement preparedStatementPessoa = connection.prepareStatement(sqlPessoa)) {

            preparedStatementPessoaJuridica.setInt(1, id);
            preparedStatementPessoaJuridica.executeUpdate();

            preparedStatementPessoa.setInt(1, id);
            preparedStatementPessoa.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

