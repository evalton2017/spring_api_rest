package br.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.projeto.api.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Empresa obj WHERE obj.cnpj= :cnpj")
	public Empresa findCnpj(@Param("cnpj")String cnpj);


}
