package br.com.caelum.cadastro;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioHelper {

	private Aluno aluno = new Aluno();
	private EditText endereco;
	private EditText nome;
	private EditText site;
	private SeekBar nota;
	private EditText telefone;
	private ImageView foto;

	public FormularioHelper(FormularioActivity formularioActivity) {
		endereco = (EditText) formularioActivity.findViewById(R.id.endereco);
		nome = (EditText) formularioActivity.findViewById(R.id.nome);
		site = (EditText) formularioActivity.findViewById(R.id.site);
		nota = (SeekBar) formularioActivity.findViewById(R.id.nota);
		telefone = (EditText) formularioActivity.findViewById(R.id.telefone);
		foto = (ImageView) formularioActivity.findViewById(R.id.foto);
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
		if (aluno.getFoto() != null) {
			carregaImagem(aluno.getFoto());
		}
		
	}
	
	public ImageView getFoto() {
		return foto;
	}

	public void carregaImagem(String fotoPath) {
		aluno.setFoto(fotoPath);
		Bitmap bitmap = BitmapFactory.decodeFile(fotoPath);
		bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
		foto.setImageBitmap(bitmap);
	}
	
}
