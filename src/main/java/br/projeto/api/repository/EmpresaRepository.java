package br.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.projeto.api.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	
	Empresa findbyCnpj(String cnpj);

}
