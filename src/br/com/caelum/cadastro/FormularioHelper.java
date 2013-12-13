package br.com.caelum.cadastro;

import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioHelper {

	private FormularioActivity formularioActivity;

	public FormularioHelper(FormularioActivity formularioActivity) {
		this.formularioActivity = formularioActivity;
	}

	public Aluno pegaAluno() {
		Aluno aluno = new Aluno();
		
		TextView endereco = (TextView) formularioActivity.findViewById(R.id.endereco);
		aluno.setEndereco(endereco.getText().toString());
		
		TextView nome = (TextView) formularioActivity.findViewById(R.id.nome);
		aluno.setNome(nome.getText().toString());
		
		TextView site = (TextView) formularioActivity.findViewById(R.id.site);
		aluno.setSite(site.getText().toString());
		
		TextView telefone = (TextView) formularioActivity.findViewById(R.id.telefone);
		aluno.setTelefone(telefone.getText().toString());
		
		SeekBar nota = (SeekBar) formularioActivity.findViewById(R.id.nota);
		aluno.setNota(nota.getProgress());
		
		return aluno;
	}

}
