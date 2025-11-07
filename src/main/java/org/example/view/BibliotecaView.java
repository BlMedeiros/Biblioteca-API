package org.example.view;

import org.example.model.Livro;
import org.example.model.Usuario;
import org.example.service.EmprestimoService;
import org.example.service.LivroService;
import org.example.service.UsuarioService;

import java.util.List;
import java.util.Scanner;

public class BibliotecaView {
    private final Scanner sc;
    private final LivroService livroService;
    private final UsuarioService usuarioService;
    private final EmprestimoService emprestimoService;

    public BibliotecaView(Scanner sc,LivroService livroService,UsuarioService usuarioService, EmprestimoService emprestimoService) {
        this.sc = sc;
        this.livroService = livroService;
        this.usuarioService = usuarioService;
        this.emprestimoService = emprestimoService;
    }

    public boolean mostrarMenu() {
        System.out.println("\n\n------ BIBLIOTECA ------");
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
                listarLivro();
                break;
            case 4:
                inserirEmprestimo();
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
        System.out.print("Digite o Nome do Livro: ");
        String livro = sc.nextLine();

        System.out.print("Digite o Autor do Livro: ");
        String autor = sc.nextLine();

        System.out.print("Digite o Ano do Livro: ");
        int ano = sc.nextInt();

        livroService.inserirLivro(livro,autor,ano);
    }

    private void inserirUsuario() {
        System.out.print("Digite o Nome do Usuario: ");
        String nome = sc.nextLine();

        System.out.print("Digite o Email do Usuario");
        String email = sc.nextLine();

        usuarioService.inserirUsuario(nome,email);
    }

    private void listarLivro() {
        List<Livro> livroList = livroService.listarLivro();

        livroList.forEach(livro -> {
            System.out.printf("ID: %-5d | Título: %-20s | Autor: %-20s | Ano do Livro: %-5d | Disponivel: %-15s%n",
                    livro.getIdLivro(),
                    livro.getTitulo(),
                    livro.getAutor(),
                    livro.getAno(),
                    livro.isDisponivel());
        });
    }

    private void listarUsuario() {
        List<Usuario> usuarioList = usuarioService.listarUsuario();

        usuarioList.forEach(usuario -> {
            System.out.printf("ID: %-5d | Nome: %-20s | Email: %-20s ",
                    usuario.getIdUsuario(),
                    usuario.getNome(),
                    usuario.getEmail());
        });

    }

    private void inserirEmprestimo() {
        listarLivro();
        System.out.print("\nInsira o Identificador do Livro: ");
        int idLivro = sc.nextInt();

        listarUsuario();
        System.out.print("\nInsira o Identificador do Usuario: ");
        int idUsuario = sc.nextInt();

        try {
            emprestimoService.inserirEmprestimo(idLivro, idUsuario);
        } finally {
            livroService.atualizarStatusLivro(idLivro);
        }
    }
}
