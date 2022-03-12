package com.Livro_Spring.domain.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_usuario;

	private String nome;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = true, unique = true )
	private Endereco endereco;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dc;

	public Usuario() {
		super();
		this.setDc(LocalDateTime.now());
	}

	public Usuario(Long id_usuario, String nome, Endereco endereco, LocalDateTime dc) {
		super();
		this.id_usuario = id_usuario;
		this.nome = nome;
		this.endereco = endereco;
		this.setDc(LocalDateTime.now());
	}

}
