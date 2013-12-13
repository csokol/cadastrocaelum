package br.com.caelum.cadastro;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.caelum.cadastro.dao.AlunoDao;
import br.com.caelum.cadastro.modelo.Aluno;

public class ListaAlunosActivity extends Activity {

	private ListView listAlunos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_alunos);
		
		listAlunos = (ListView) findViewById(R.id.lista_alunos);
		
		listAlunos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicao,
					long id) {
				Toast.makeText(ListaAlunosActivity.this, "Posição selecionada: " + posicao, Toast.LENGTH_LONG).show();
			}
		});
		
		listAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				Object item = listAlunos.getItemAtPosition(posicao);
				Toast.makeText(ListaAlunosActivity.this, "aluno: " + item, Toast.LENGTH_LONG).show();
				return true;
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		carregaLista();
	}

	private void carregaLista() {
		AlunoDao alunoDao = new AlunoDao(this);
		List<Aluno> alunos = alunoDao.getLista();
		alunoDao.close();
		listAlunos.setAdapter(new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_principal, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		
		int itemId = item.getItemId();
		
		switch(itemId) {
			case R.id.menu_novo:
				Intent intent = new Intent(this, FormularioActivity.class);
				startActivity(intent);
				return true;
			default:
				Toast.makeText(this, "Item selecionado " + itemId, Toast.LENGTH_LONG).show();
				return false;
		}
	}

}
