package br.projeto.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.projeto.api.entities.Empresa;
import br.projeto.api.repository.EmpresaRepository;


@SpringBootApplication
public class ApiSpringSwaggerApplication {

	@Autowired
	private EmpresaRepository empRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(ApiSpringSwaggerApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
	
		Empresa empresa = new Empresa();
		empresa.setCnpj("0235566600010");
		empresa.setDataAtualizacao(new Date());
		empresa.setDataCriacao(new Date());
		empresa.setRazaoSocial("Empresas Edivan");
		
		empRepo.save(empresa);
		
		Empresa duke =empRepo.findCnpj("002235550001");
		duke.setRazaoSocial("Duke Corajoso");
		empRepo.save(duke);
		System.out.println(empRepo.findCnpj("002235550001"));
			
			
		};
	}

}
