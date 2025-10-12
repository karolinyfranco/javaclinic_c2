package br.faesa.javaclinic_c2.principal;

import br.faesa.javaclinic_c2.conexion.ConexaoMySQL;

public class Principal {
    public static void main(String[] args) {
        // Teste de conexão
        ConexaoMySQL conexao = new ConexaoMySQL();
        conexao.connect();
        conexao.close();
    }
}

