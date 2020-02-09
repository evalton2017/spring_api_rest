package br.projeto.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.projeto.api.dto.EmpresaDTO;
import br.projeto.api.entities.Empresa;
import br.projeto.api.services.EmpresaService;

@CrossOrigin
@RestController
@RequestMapping("/empresa")
public class EmpresaController {
	
	@Autowired
	private EmpresaService service;
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public @ResponseBody Empresa find(@PathVariable("codigo") String cnpj) throws Exception{
		Empresa emp =  service.buscarCnpj(cnpj);
		return emp;		
	}
	
	
//	@RequestMapping(value="/{cnpj}",method=RequestMethod.GET)
//	public ResponseEntity<Empresa> buscar(@PathVariable String cnpj) throws Exception{
//		Empresa emp = service.buscarCnpj(cnpj);
//		return ResponseEntity.ok().body(emp); 
//	}
//	
	
	@RequestMapping(method=RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<Empresa>> empresas(){
		List<Empresa> empresas = service.listar();
		return ResponseEntity.ok().body(empresas);
		
	}
	
	@RequestMapping(method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmpresaDTO> salvar(@Valid @RequestBody Empresa empresa) throws Exception{
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//				  .path("/empresa").buildAndExpand(obj.getId()).toUri();
//		return ResponseEntity.created(uri).build();
		Empresa emp = service.salvar(empresa);
		EmpresaDTO dto = new EmpresaDTO();
		dto.setRazaoSocial(emp.getRazaoSocial());
		return ResponseEntity.ok(dto);
	}
	
	
}
