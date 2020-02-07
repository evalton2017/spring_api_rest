package br.projeto.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.projeto.api.enums.PerfilEnum;

@Entity
@Table(name="funcionario")
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	@Column(name="email", unique = true)
	private String email;
	private String senha;
	@Column(name="cpf", unique = true)
	private String cpf;
	@Column(name="valor_hora", nullable = true)
	private BigDecimal valorHora;
	private Float qtdHorasTrabalhadas;
	private Float qtdHorasAlmoco;
	@Enumerated(EnumType.STRING)
	@Column(name="perfil", nullable = false)
	private PerfilEnum perfil;
	@Column(name="data_criacao")
	private Date dataCriacao;
	@Column(name="data_atualizacao")
	private Date dataAtualizacao;
	@ManyToOne(fetch= FetchType.EAGER)
	private Empresa empresa;
	@OneToMany(mappedBy = "funcionario", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
	private List<Lancamento> lancamentos;
	
	public Funcionario() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public BigDecimal getValorHora() {
		return valorHora;
	}
	public void setValorHora(BigDecimal valorHora) {
		this.valorHora = valorHora;
	}
	public Float getQtdHorasTrabalhadas() {
		return qtdHorasTrabalhadas;
	}
	public void setQtdHorasTrabalhadas(Float qtdHorasTrabalhadas) {
		this.qtdHorasTrabalhadas = qtdHorasTrabalhadas;
	}
	public Float getQtdHorasAlmoco() {
		return qtdHorasAlmoco;
	}
	public void setQtdHorasAlmoco(Float qtdHorasAlmoco) {
		this.qtdHorasAlmoco = qtdHorasAlmoco;
	}
	public PerfilEnum getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date();
	}
	
	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		dataCriacao = atual;
		dataAtualizacao = atual;
	}
	
	
	
}
