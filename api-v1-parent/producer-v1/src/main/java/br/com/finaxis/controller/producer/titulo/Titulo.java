package br.com.finaxis.controller.producer.titulo;

import java.io.Serializable;
import java.util.Date;

public class Titulo implements Serializable {

	private static final long serialVersionUID = -6491449962350888098L;
	private String codigo;
	private String nome;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
