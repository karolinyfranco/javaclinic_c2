package br.faesa.javaclinic_c2.principal;

import br.faesa.javaclinic_c2.controller.ControllerConsulta;
import br.faesa.javaclinic_c2.controller.ControllerMedico;
import br.faesa.javaclinic_c2.controller.ControllerPaciente;
import br.faesa.javaclinic_c2.model.Consulta;
import br.faesa.javaclinic_c2.model.Especialidade;
import br.faesa.javaclinic_c2.model.Medico;
import br.faesa.javaclinic_c2.model.Paciente;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

// *** MAIN FEITO PARA TESTES, NÃO É O PRODUTO FINAL ***

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ControllerMedico ctrlMedico = new ControllerMedico();
        ControllerPaciente ctrlPaciente = new ControllerPaciente();
        ControllerConsulta ctrlConsulta = new ControllerConsulta();

        while (true) {
            System.out.println("\n===== JAVA CLINIC - MENU PRINCIPAL =====");
            System.out.println("1. Gerenciar Médicos");
            System.out.println("2. Gerenciar Pacientes");
            System.out.println("3. Gerenciar Consultas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> menuMedicos(sc, ctrlMedico);
                case 2 -> menuPacientes(sc, ctrlPaciente);
                case 3 -> menuConsultas(sc, ctrlConsulta);
                case 0 -> {
                    System.out.println("Saindo... até logo!");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // ===================== MÉDICOS =====================
    private static void menuMedicos(Scanner sc, ControllerMedico ctrlMedico) {
        System.out.println("\n=== MENU MÉDICOS ===");
        System.out.println("1. Inserir");
        System.out.println("2. Listar");
        System.out.println("3. Atualizar");
        System.out.println("4. Excluir");
        System.out.print("Escolha: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1 -> {
                System.out.print("CRM: ");
                String crm = sc.nextLine();
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Telefone: ");
                String telefone = sc.nextLine();
                System.out.print("Endereço: ");
                String endereco = sc.nextLine();
                System.out.print("Especialidade (ex: CARDIOLOGIA): ");
                String esp = sc.nextLine().toUpperCase();

                Medico m = new Medico(nome, email, endereco, telefone, crm, Especialidade.valueOf(esp));
                ctrlMedico.inserir(m);
            }
            case 2 -> {
                List<Medico> medicos = ctrlMedico.listar();
                medicos.forEach(System.out::println);
            }
            case 3 -> {
                System.out.print("CRM do médico a atualizar: ");
                String crm = sc.nextLine();
                System.out.print("Novo nome: ");
                String nome = sc.nextLine();
                System.out.print("Novo email: ");
                String email = sc.nextLine();
                System.out.print("Novo telefone: ");
                String telefone = sc.nextLine();
                System.out.print("Novo endereço: ");
                String endereco = sc.nextLine();
                System.out.print("Nova especialidade: ");
                String esp = sc.nextLine().toUpperCase();

                Medico m = new Medico(nome, email, endereco, telefone, crm, Especialidade.valueOf(esp));
                ctrlMedico.atualizar(m);
            }
            case 4 -> {
                System.out.print("CRM do médico a excluir: ");
                String crm = sc.nextLine();
                ctrlMedico.excluir(crm);
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    // ===================== PACIENTES =====================
    private static void menuPacientes(Scanner sc, ControllerPaciente ctrlPaciente) {
        System.out.println("\n=== MENU PACIENTES ===");
        System.out.println("1. Inserir");
        System.out.println("2. Listar");
        System.out.println("3. Listar consultas de um paciente");
        System.out.println("4. Atualizar");
        System.out.println("5. Excluir");
        System.out.print("Escolha: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1 -> {
                System.out.print("CPF: ");
                String cpf = sc.nextLine();
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Telefone: ");
                String telefone = sc.nextLine();
                System.out.print("Endereço: ");
                String endereco = sc.nextLine();

                Paciente p = new Paciente(nome, email, endereco, telefone, cpf);
                ctrlPaciente.inserir(p);
            }
            case 2 -> {
                List<Paciente> pacientes = ctrlPaciente.listar();
                pacientes.forEach(System.out::println);
            }
            case 3 -> {
                System.out.print("Digite o CPF do paciente: ");
                String cpf = sc.nextLine();
                ctrlPaciente.listarConsultasDoPaciente(cpf);
            }
            case 4 -> {
                System.out.print("CPF do paciente a atualizar: ");
                String cpf = sc.nextLine();
                System.out.print("Novo nome: ");
                String nome = sc.nextLine();
                System.out.print("Novo email: ");
                String email = sc.nextLine();
                System.out.print("Novo telefone: ");
                String telefone = sc.nextLine();
                System.out.print("Novo endereço: ");
                String endereco = sc.nextLine();

                Paciente p = new Paciente(nome, email, endereco, telefone, cpf);
                ctrlPaciente.atualizar(p);
            }
            case 5 -> {
                System.out.print("CPF do paciente a excluir: ");
                String cpf = sc.nextLine();
                ctrlPaciente.excluir(cpf);
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    // ===================== CONSULTAS =====================
    private static void menuConsultas(Scanner sc, ControllerConsulta ctrlConsulta) {
        System.out.println("\n=== MENU CONSULTAS ===");
        System.out.println("1. Agendar");
        System.out.println("2. Listar");
        System.out.println("3. Cancelar");
        System.out.print("Escolha: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1 -> {
                System.out.print("CRM do médico: ");
                String crm = sc.nextLine();
                System.out.print("CPF do paciente: ");
                String cpf = sc.nextLine();
                System.out.print("Especialidade (ex: CARDIOLOGIA): ");
                String esp = sc.nextLine().toUpperCase();

                System.out.print("Data e hora da consulta (AAAA-MM-DDTHH:MM): ");
                String dataStr = sc.nextLine();
                LocalDateTime data = LocalDateTime.parse(dataStr);

                Consulta c = new Consulta(0, crm, cpf, Especialidade.valueOf(esp), data);
                ctrlConsulta.inserir(c);
            }
            case 2 -> {
                List<Consulta> consultas = ctrlConsulta.listar();
                consultas.forEach(System.out::println);
            }
            case 3 -> {
                System.out.print("ID da consulta a cancelar: ");
                long id = sc.nextLong();
                sc.nextLine();
                ctrlConsulta.excluir(id);
            }
            default -> System.out.println("Opção inválida!");
        }
    }
}

