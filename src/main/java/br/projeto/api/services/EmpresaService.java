package br.projeto.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.projeto.api.entities.Empresa;
import br.projeto.api.repository.EmpresaRepository;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository empRep;
	
	
	public List<Empresa> listar(){
		List<Empresa> empresas = empRep.findAll();
		return empresas;
		
	}
	
	public Empresa salvar(Empresa empresa) {
	  Empresa emp = empRep.save(empresa);
	  return emp;
	}

}
