package br.com.caelum.cadastro;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
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
	private Aluno alunoSelecionado;
	private ListView listAlunos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_alunos);
		
		listAlunos = (ListView) findViewById(R.id.lista_alunos);
		registerForContextMenu(listAlunos);
		
		listAlunos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicao,
					long id) {
				Intent edicao = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
				Aluno aluno = (Aluno) adapter.getItemAtPosition(posicao);
				edicao.putExtra(Extras.ALUNO_SELECIONADO, aluno);
				startActivity(edicao);
			}
		});
		
		listAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				alunoSelecionado = (Aluno) listAlunos.getItemAtPosition(posicao);
				return false;
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
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add("Ligar");
		menu.add("Enviar SMS");
		menu.add("Achar no mapa");
		menu.add("Navegar no site");
		MenuItem deletar = menu.add("Deletar");
		deletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlunoDao dao = new AlunoDao(ListaAlunosActivity.this);
				dao.deletar(alunoSelecionado);
				dao.close();
				carregaLista();
				return false;
			}
		});
		menu.add("Enviar email");
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
