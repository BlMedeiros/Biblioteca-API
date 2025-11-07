package org.example.view;

import org.example.service.LivroService;

import java.util.Scanner;

public class BibliotecaView {
    private final Scanner sc;
    private final LivroService livroService;

    public BibliotecaView(Scanner sc,LivroService livroService) {
        this.sc = sc;
        this.livroService = livroService;
    }

    public boolean mostrarMenu() {
        System.out.println("[1] Cadastrar Livro");
        System.out.println("[2] Cadastrar Usuario");
        System.out.println("[3] Consultar Todos os Livros Cadastrados");
        System.out.println("[4] Registrar Empréstimo de Livro");
        System.out.println("[5] Registrar Devolução de Livro");
        System.out.println("[0] Sair do Sistema");

        System.out.println("\n Escolha: ");

        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                inserirLivro();
                break;
            case 2:
                inserirUsuario();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 0:
                System.out.println("Encerrando o Sistema....");
                return false;
            default:
                System.out.println("Opção Inválida! Tente Novamente.");
        }

        return true;
    }

    private void inserirLivro() {
        System.out.println("Digite o Nome do Livro: ");
        String livro = sc.nextLine();

        System.out.println("Digite o Autor do Livro: ");
        String autor = sc.nextLine();

        System.out.println("Digite o Ano do Livro: ");
        int ano = sc.nextInt();

        livroService.inserirLivro(livro,autor,ano);
    }
}
