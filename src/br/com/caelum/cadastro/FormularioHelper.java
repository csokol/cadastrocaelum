package br.com.caelum.cadastro;

import android.widget.SeekBar;
import android.widget.TextView;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioHelper {

	private Aluno aluno = new Aluno();
	private TextView endereco;
	private TextView nome;
	private TextView site;
	private SeekBar nota;
	private TextView telefone;

	public FormularioHelper(FormularioActivity formularioActivity) {
		endereco = (TextView) formularioActivity.findViewById(R.id.endereco);
		nome = (TextView) formularioActivity.findViewById(R.id.nome);
		site = (TextView) formularioActivity.findViewById(R.id.site);
		nota = (SeekBar) formularioActivity.findViewById(R.id.nota);
		telefone = (TextView) formularioActivity.findViewById(R.id.telefone);
	}

	public Aluno pegaAluno() {
		
		aluno.setEndereco(endereco.getText().toString());
		aluno.setNome(nome.getText().toString());
		aluno.setSite(site.getText().toString());
		aluno.setTelefone(telefone.getText().toString());
		aluno.setNota(nota.getProgress());
		
		return aluno;
	}

	public void colocaNoFormulario(Aluno aluno) {
		this.aluno = aluno;
		endereco.setText(aluno.getEndereco());
		nome.setText(aluno.getNome());
		site.setText(aluno.getSite());
		nota.setProgress((int) aluno.getNota());
		telefone.setText(aluno.getTelefone());
	}

}
