package br.projeto.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.projeto.api.entities.Usuario;
import br.projeto.api.repository.UsuarioRepository;
import br.projeto.api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository rep;
	
	public Optional<Usuario> buscarPorEmail(String email){
		return Optional.ofNullable(this.rep.findByEmail(email));
				
	}
}
