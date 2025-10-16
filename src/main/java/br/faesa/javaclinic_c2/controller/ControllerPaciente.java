package br.faesa.javaclinic_c2.controller;

import br.faesa.javaclinic_c2.conexion.ConexaoMySQL;
import br.faesa.javaclinic_c2.model.Paciente;
import br.faesa.javaclinic_c2.utils.ValidatorUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControllerPaciente {
    ConexaoMySQL conexao = new ConexaoMySQL();
    ValidatorUtils validator = new ValidatorUtils();

    public void inserir(Paciente paciente) {
        String sql = "INSERT INTO paciente (cpf, nome, email, telefone, endereco) VALUES (?, ?, ?, ?, ?)";

        try {
            conexao.connect();
            PreparedStatement stmt = conexao.getConn().prepareStatement(sql);

            stmt.setString(1, paciente.getCpf());
            stmt.setString(2, paciente.getNome());
            stmt.setString(3, paciente.getEmail());
            stmt.setString(4, paciente.getTelefone());
            stmt.setString(5, paciente.getEndereco());
            stmt.executeUpdate();

            System.out.println("Paciente inserido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir paciente: " + e.getMessage());
        } finally {
            conexao.close();
        }
    }

    public void atualizar(Paciente paciente) {
        String sql = "UPDATE paciente SET nome=?, email=?, telefone=?, endereco=? WHERE cpf=?";

        try {
            conexao.connect();

            // Verifica se existe antes de atualizar
            if (!validator.existePaciente(conexao, paciente.getCpf())) {
                System.out.println("Paciente com CPF " + paciente.getCpf() + " não encontrado.");
                return;
            }

            PreparedStatement stmt = conexao.getConn().prepareStatement(sql);

            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getEmail());
            stmt.setString(3, paciente.getTelefone());
            stmt.setString(4, paciente.getEndereco());
            stmt.setString(5, paciente.getCpf());
            stmt.executeUpdate();

            System.out.println("Paciente atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar paciente: " + e.getMessage());
        } finally {
            conexao.close();
        }
    }

    public void excluir(String cpf) {
        String sql = "DELETE FROM paciente WHERE cpf=?";

        try {
            conexao.connect();

            // Verifica se existe antes de excluir
            if (!validator.existePaciente(conexao, cpf)) {
                System.out.println("Paciente com CPF " + cpf + " não encontrado.");
                return;
            }

            PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            System.out.println("Paciente excluído com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir paciente: " + e.getMessage());
        } finally {
            conexao.close();
        }
    }

    public List<Paciente> listar() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM paciente";

        try {
            conexao.connect();
            PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Paciente paciente = new Paciente(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getString("cpf")
                );
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pacientes: " + e.getMessage());
        } finally {
            conexao.close();
        }
        return pacientes;
    }

    public void listarConsultasDoPaciente(String cpfPaciente) {
        try {
            conexao.connect();

            // Verifica se o paciente existe
            String checkSql = "SELECT 1 FROM paciente WHERE cpf = ?";
            PreparedStatement checkStmt = conexao.getConn().prepareStatement(checkSql);
            checkStmt.setString(1, cpfPaciente);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Paciente com CPF " + cpfPaciente + " não encontrado.");
                return;
            }

            // Se o paciente existe, lista as consultas
            ControllerConsulta ctrlConsulta = new ControllerConsulta();
            ctrlConsulta.listarPorPaciente(cpfPaciente);

        } catch (SQLException e) {
            System.out.println("Erro ao buscar consultas do paciente: " + e.getMessage());
        } finally {
            conexao.close();
        }
    }
}
