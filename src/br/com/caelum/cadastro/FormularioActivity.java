package br.com.caelum.cadastro;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import br.com.caelum.cadastro.dao.AlunoDao;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioActivity extends Activity {

	private static final int TIRAR_FOTO = 123;
	private Button botaoGravar;
	private String fotoPath;
	private FormularioHelper helper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		botaoGravar = (Button) findViewById(R.id.botao);
		helper = new FormularioHelper(FormularioActivity.this);
		ImageView foto = helper.getFoto();
		foto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				fotoPath = Environment.getExternalStorageDirectory() + "/" + System.currentTimeMillis() + ".jpg";
				Intent tirarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				tirarFoto.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(fotoPath)));
				startActivityForResult(tirarFoto, TIRAR_FOTO);
			}
		});
		
		Aluno aluno = (Aluno) getIntent().getSerializableExtra(Extras.ALUNO_SELECIONADO);
		if (aluno != null) {
			Toast.makeText(this, aluno.getFoto(), Toast.LENGTH_LONG).show();
			botaoGravar.setText("Alterar");
			helper.colocaNoFormulario(aluno);
		}
		
		botaoGravar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Aluno aluno = helper.pegaAluno();
				AlunoDao alunoDao = new AlunoDao(FormularioActivity.this);
				if (aluno.getId() == null) {
					alunoDao.salva(aluno);
				} else {
					alunoDao.atualiza(aluno);
				}
				alunoDao.close();
				finish();
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == TIRAR_FOTO) {
			if (resultCode == RESULT_OK) {
				helper.carregaImagem(this.fotoPath);
			} else {
				this.fotoPath = null;
			}
		}
	}

}
