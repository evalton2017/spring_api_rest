package br.projeto.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.projeto.api.entities.Usuario;
import br.projeto.api.repository.UsuarioRepository;
import br.projeto.api.security.enums.PerfilEnum;
import br.projeto.api.utils.SenhaUtils;


@SpringBootApplication
public class ApiSpringSwaggerApplication {
	
	@Autowired
	private UsuarioRepository userRep;

	public static void main(String[] args) {
		SpringApplication.run(ApiSpringSwaggerApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
	
			List<Usuario> user = userRep.findAll();	
			if(user.isEmpty()) {
				Usuario usuario = new Usuario();
				usuario.setEmail("duke@gmail.com");
				usuario.setPerfil(PerfilEnum.ROLE_ADMIN);
				usuario.setSenha(SenhaUtils.gerarBcript("123456"));
				userRep.save(usuario);
			}
		};
	}

}
