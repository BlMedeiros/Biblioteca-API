package org.example.service;

import org.example.model.Livro;
import org.example.model.Usuario;
import org.example.repository.UsuarioRepository;

import java.sql.SQLException;
import java.util.List;

public class UsuarioService {

    UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void inserirUsuario(String nome, String email) {
        if (nome.isBlank() || email.isBlank()) {
            System.err.println("O Usuario Não Pode ser Nulo!");
            return;
        } else {
            try {
                Usuario usuario = new Usuario(nome, email);

                usuarioRepository.inserirUsuario(usuario);
            } catch (RuntimeException e) {
                System.err.println("Ocorreu um Erro ao Inserir o Usuario no Banco de Dados: " + e.getMessage());
                e.printStackTrace();

                throw new RuntimeException("Ocorreu um Erro Interno ao Cadastrar no Banco de Dados");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<Usuario> listarUsuario() {
        try {
            List<Usuario> usuarioList = usuarioRepository.listarUsuario();

            return usuarioList;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
