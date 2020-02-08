package br.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.projeto.api.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
