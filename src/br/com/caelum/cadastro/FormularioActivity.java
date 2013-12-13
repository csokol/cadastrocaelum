package br.com.caelum.cadastro;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.caelum.cadastro.dao.AlunoDao;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioActivity extends Activity {

	private Button botaoGravar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		botaoGravar = (Button) findViewById(R.id.botao);
		botaoGravar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FormularioHelper helper = new FormularioHelper(FormularioActivity.this);
				Aluno aluno = helper.pegaAluno();
				AlunoDao alunoDao = new AlunoDao(FormularioActivity.this);
				alunoDao.salva(aluno);
				alunoDao.close();
				finish();
			}
		});
	}

}
