package br.projeto.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.projeto.api.dto.EmpresaDTO;
import br.projeto.api.entities.Empresa;
import br.projeto.api.services.EmpresaService;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
	
	@Autowired
	private EmpresaService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Empresa>> empresas(){
		List<Empresa> empresas = service.listar();
		return ResponseEntity.ok().body(empresas);
		
	}
	
	@RequestMapping(method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmpresaDTO> salvar(@RequestBody Empresa empresa) throws Exception{
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//				  .path("/empresa").buildAndExpand(obj.getId()).toUri();
//		return ResponseEntity.created(uri).build();
		Empresa emp = service.salvar(empresa);
		EmpresaDTO dto = new EmpresaDTO();
		dto.setRazaoSocial(emp.getRazaoSocial());
		return ResponseEntity.ok(dto);
	}
	
}
