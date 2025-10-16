package br.faesa.javaclinic_c2.controller;

import br.faesa.javaclinic_c2.conexion.ConexaoMySQL;
import br.faesa.javaclinic_c2.model.Medico;
import br.faesa.javaclinic_c2.model.Especialidade;
import br.faesa.javaclinic_c2.utils.ValidatorUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControllerMedico {
    ConexaoMySQL conexao = new ConexaoMySQL();
    ValidatorUtils validator = new ValidatorUtils();

    public void inserir(Medico medico) {
        String sql = "INSERT INTO medico (crm, nome, email, especialidade, telefone, endereco) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            conexao.connect();
            PreparedStatement stmt = conexao.getConn().prepareStatement(sql);

            stmt.setString(1, medico.getCrm());
            stmt.setString(2, medico.getNome());
            stmt.setString(3, medico.getEmail());
            stmt.setString(4, medico.getEspecialidade().name());
            stmt.setString(5, medico.getTelefone());
            stmt.setString(6, medico.getEndereco());
            stmt.executeUpdate();

            System.out.println("Médico inserido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir médico: " + e.getMessage());
        } finally {
            conexao.close();
        }
    }

    public void atualizar(Medico medico) {
        String sql = "UPDATE medico SET nome=?, email=?, especialidade=?, telefone=?, endereco=? WHERE crm=?";

        try {
            conexao.connect();

            // Verifica se existe antes de atualizar
            if (!validator.existeMedico(conexao, medico.getCrm())) {
                System.out.println("Médico com CRM " + medico.getCrm() + " não encontrado.");
                return;
            }

            PreparedStatement stmt = conexao.getConn().prepareStatement(sql);

            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getEmail());
            stmt.setString(3, medico.getEspecialidade().name());
            stmt.setString(4, medico.getTelefone());
            stmt.setString(5, medico.getEndereco());
            stmt.setString(6, medico.getCrm());
            stmt.executeUpdate();

            System.out.println("Médico atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar médico: " + e.getMessage());
        } finally {
            conexao.close();
        }
    }

    public void excluir(String crm) {
        String sql = "DELETE FROM medico WHERE crm=?";

        try {
            conexao.connect();

            // Verifica se existe antes de excluir
            if (!validator.existeMedico(conexao, crm)) {
                System.out.println("Médico com CRM " + crm + " não encontrado.");
                return;
            }

            PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
            stmt.setString(1, crm);
            stmt.executeUpdate();
            System.out.println("Médico excluído com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir médico: " + e.getMessage());
        } finally {
            conexao.close();
        }
    }

    public List<Medico> listar() {
        List<Medico> medicos = new ArrayList<>();
        String sql = "SELECT * FROM medico";

        try {
            conexao.connect();
            PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Medico medico = new Medico(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getString("crm"),
                        Especialidade.valueOf(rs.getString("especialidade"))
                );
                medicos.add(medico);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar médicos: " + e.getMessage());
        } finally {
            conexao.close();
        }
        return medicos;
    }
}
