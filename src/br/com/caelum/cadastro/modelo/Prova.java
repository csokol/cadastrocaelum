package br.com.caelum.cadastro.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Prova implements Serializable {
	
	private String data;
	private String materia;
	private String descricao;
	private List<String> topicos = new ArrayList<String>();
	
	public Prova(String data, String materia) {
		this.data = data;
		this.materia = materia;
	}
	
	@Override
	public String toString() {
		return materia + " - " + data;
	}

	public String getData() {
		return data;
	}

	public String getMateria() {
		return materia;
	}

	public String getDescricao() {
		return descricao;
	}

	public List<String> getTopicos() {
		return topicos;
	}

}
