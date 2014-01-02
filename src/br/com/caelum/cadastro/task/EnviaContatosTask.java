package br.com.caelum.cadastro.task;

import java.util.List;

import br.com.caelum.cadastro.converter.AlunoConverter;
import br.com.caelum.cadastro.dao.AlunoDao;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.support.WebClient;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class EnviaContatosTask extends AsyncTask<Object, Object, String>{
	
	private Context context;
	private ProgressDialog dialog;
	
	public EnviaContatosTask(Context context) {
		this.context = context;
	}

	@Override
	protected String doInBackground(Object... params) {
		AlunoDao alunoDao = new AlunoDao(context);
		List<Aluno> alunos = alunoDao.getLista();
		String json = new AlunoConverter().toJson(alunos);
		Log.i("json", json);
		
		WebClient client = new WebClient("http://www.caelum.com.br/mobile");
		String response = client.post(json);
		
		alunoDao.close();
		return response;
	}
	
	@Override
	protected void onPostExecute(String result) {
		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
		dialog.dismiss();
	}
	
	@Override
	protected void onPreExecute() {
		dialog = ProgressDialog.show(context, "Aguarde...", "Envio de dados pra web", true, true);
	}

}
