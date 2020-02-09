package br.projeto.api.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.projeto.api.entities.Usuario;

@Service
public interface UsuarioService {

	Optional<Usuario> buscarPorEmail(String email);
}
