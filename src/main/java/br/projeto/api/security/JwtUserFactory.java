package br.projeto.api.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.projeto.api.entities.Usuario;
import br.projeto.api.security.enums.PerfilEnum;

public class JwtUserFactory {

	private JwtUserFactory() {
		
	}
	
	//converte e gera um JwtUser com base nos dados 
	public static JwtUser create(Usuario usuario) {
		return new JwtUser(usuario.getId(),usuario.getEmail(), usuario.getSenha(), mapToGrantedAuthorities(usuario.getPerfil()));
	}
	
	//converte o perfil para o formato usado pelo spring
	
	private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfilEnum){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities;
	}
	
	
}
