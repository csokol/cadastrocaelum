package br.com.caelum.cadastro.converter;

import java.util.List;

import org.json.JSONException;
import org.json.JSONStringer;

import br.com.caelum.cadastro.modelo.Aluno;

public class AlunoConverter {

	public String toJson(List<Aluno> alunos) {
		try {
		JSONStringer jsonStringer = new JSONStringer();
			jsonStringer.object().key("list").array().object().key("aluno").array();
			
			for (Aluno aluno : alunos) {
				jsonStringer.object()
					.key("nome").value(aluno.getNome())
					.key("nota").value(aluno.getNota())
					.key("endereco").value(aluno.getEndereco())
					.key("foto").value(aluno.getFoto())
					.key("id").value(aluno.getId())
					.key("site").value(aluno.getSite())
					.key("telefone").value(aluno.getTelefone())
					.endObject();
			}
			
			return jsonStringer.endArray().endObject()
					.endArray().endObject().toString();
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}
	
}
