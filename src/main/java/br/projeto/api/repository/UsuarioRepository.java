package br.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.projeto.api.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);
}
