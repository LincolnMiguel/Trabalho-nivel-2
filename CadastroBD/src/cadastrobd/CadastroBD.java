package cadastrobd;

import java.util.Scanner;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridicaDAO;

public class CadastroBD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
        int opcao;

        do {
            System.out.println("=== Sistema de Cadastro ===");
            System.out.println("1. Incluir");
            System.out.println("2. Alterar");
            System.out.println("3. Excluir");
            System.out.println("4. Exibir por ID");
            System.out.println("5. Exibir todos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opçao: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Escolha o tipo (Fisica/Juridica): ");
                    String tipoInclusao = scanner.nextLine();
                    if (tipoInclusao.equalsIgnoreCase("Fisica")) {
                        incluirPessoaFisica(scanner, pessoaFisicaDAO);
                    } else if (tipoInclusao.equalsIgnoreCase("Juridica")) {
                        incluirPessoaJuridica(scanner, pessoaJuridicaDAO);
                    } else {
                        System.out.println("Tipo invalido.");
                    }
                    break;

                case 2:
                    System.out.print("Escolha o tipo (Fisica/Juridica): ");
                    String tipoAlteracao = scanner.nextLine();
                    if (tipoAlteracao.equalsIgnoreCase("Fisica")) {
                        alterarPessoaFisica(scanner, pessoaFisicaDAO);
                    } else if (tipoAlteracao.equalsIgnoreCase("Juridica")) {
                        alterarPessoaJuridica(scanner, pessoaJuridicaDAO);
                    } else {
                        System.out.println("Tipo invalido.");
                    }
                    break;

                case 3:
                    System.out.print("Escolha o tipo (Fisica/Juridica): ");
                    String tipoExclusao = scanner.nextLine();
                    if (tipoExclusao.equalsIgnoreCase("Fisica")) {
                        excluirPessoaFisica(scanner, pessoaFisicaDAO);
                    } else if (tipoExclusao.equalsIgnoreCase("Juridica")) {
                        excluirPessoaJuridica(scanner, pessoaJuridicaDAO);
                    } else {
                        System.out.println("Tipo invalido.");
                    }
                    break;

                case 4:
                    System.out.print("Escolha o tipo (Fisica/Juridica): ");
                    String tipoExibicaoPorId = scanner.nextLine();
                    if (tipoExibicaoPorId.equalsIgnoreCase("Fisica")) {
                        exibirPessoaFisica(scanner, pessoaFisicaDAO);
                    } else if (tipoExibicaoPorId.equalsIgnoreCase("Juridica")) {
                        exibirPessoaJuridica(scanner, pessoaJuridicaDAO);
                    } else {
                        System.out.println("Tipo invalido.");
                    }
                    break;

                case 5:
                    System.out.print("Escolha o tipo (Fisica/Juridica): ");
                    String tipoExibicaoTodos = scanner.nextLine();
                    if (tipoExibicaoTodos.equalsIgnoreCase("Fisica")) {
                        exibirTodasPessoasFisicas(pessoaFisicaDAO);
                    } else if (tipoExibicaoTodos.equalsIgnoreCase("Juridica")) {
                        exibirTodasPessoasJuridicas(pessoaJuridicaDAO);
                    } else {
                        System.out.println("Tipo invalido.");
                    }
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção invalida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    public static void incluirPessoaFisica(Scanner scanner, PessoaFisicaDAO pessoaFisicaDAO) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
    }

    public static void incluirPessoaJuridica(Scanner scanner, PessoaJuridicaDAO pessoaJuridicaDAO) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
    }

    public static void alterarPessoaFisica(Scanner scanner, PessoaFisicaDAO pessoaFisicaDAO) {
    }

    public static void alterarPessoaJuridica(Scanner scanner, PessoaJuridicaDAO pessoaJuridicaDAO) {
    }

    public static void excluirPessoaFisica(Scanner scanner, PessoaFisicaDAO pessoaFisicaDAO) {
        System.out.print("Informe o ID da Pessoa Fisica para excluir: ");
        int id = scanner.nextInt();
    }

    public static void excluirPessoaJuridica(Scanner scanner, PessoaJuridicaDAO pessoaJuridicaDAO) {
        System.out.print("Informe o ID da Pessoa Juridica para excluir: ");
        int id = scanner.nextInt();
    }

    public static void exibirPessoaFisica(Scanner scanner, PessoaFisicaDAO pessoaFisicaDAO) {
        System.out.print("Informe o ID da Pessoa Fisica: ");
        int id = scanner.nextInt();
    }

    public static void exibirPessoaJuridica(Scanner scanner, PessoaJuridicaDAO pessoaJuridicaDAO) {
        System.out.print("Informe o ID da Pessoa Juridica: ");
        int id = scanner.nextInt();
    }

    public static void exibirTodasPessoasFisicas(PessoaFisicaDAO pessoaFisicaDAO) {
    }

    public static void exibirTodasPessoasJuridicas(PessoaJuridicaDAO pessoaJuridicaDAO) {
    }
}

