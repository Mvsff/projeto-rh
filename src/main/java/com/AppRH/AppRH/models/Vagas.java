package com.AppRH.AppRH.models;


import java.io.Serializable;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import jakarta.validation.constraints.NotEmpty;

@Entity
public class Vagas implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Gera um id, o código de cada participante, a partir do generated value AUTO.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	
	//Not Empty para a variável não ficar sem valor
	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String descricao;
	
	@NotEmpty
	private String data;
	
	@NotEmpty
	private String salario;
	
	//Fazer a ligação ente lista de dados e a vaga
	//Ou seja, quando uma vaga for deletada, um candidato será deletado tbm
	@OneToMany(mappedBy = "vagas", cascade = CascadeType.REMOVE)
	private List<Candidato> candidatos;

	
	//Fiz todo esse codigo abaixo utilizando o get and setters, basta clicar no botão direito, 
	//ir em "source", e "Generate getters and setters", e marquei todas as opções, menos a de UID.
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public List<Candidato> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<Candidato> candidatos) {
		this.candidatos = candidatos;
	}
	
	
}
