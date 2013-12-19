package br.com.caelum.cadastro.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Aluno;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlunoDao extends SQLiteOpenHelper {

	private static final String TABELA = "FJ57";
	private static final String[] COLUNAS = {"id", "nome", "telefone", "endereco", "site", "nota", "foto"};

	public AlunoDao(Context context) {
		super(context, "CadastroCaelum", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABELA + " " +
				"(id INTEGER PRIMARY KEY, " +
				"nome TEXT UNIQUE NOT NULL, " +
				"telefone TEXT, " +
				"endereco TEXT, " +
				"site TEXT, " +
				"nota REAL, " +
				"foto TEXT);");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists "+TABELA+";");
	}

	public void salva(Aluno aluno) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = toContentValues(aluno);
		db.insert(TABELA, null, values);
	}

	private ContentValues toContentValues(Aluno aluno) {
		ContentValues values = new ContentValues();
		values.put("nome", aluno.getNome());
		values.put("endereco", aluno.getEndereco());
		values.put("telefone", aluno.getTelefone());
		values.put("site", aluno.getSite());
		values.put("nome", aluno.getNome());
		values.put("nota", aluno.getNota());
		values.put("foto", aluno.getFoto());
		return values;
	}
	
	public void deletar(Aluno aluno) {
		String[] args = {aluno.getId().toString()};
		getWritableDatabase().delete(TABELA, "id=?", args);
	}
	
	public List<Aluno> getLista() {
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		Cursor cursor = getReadableDatabase().query(TABELA, COLUNAS, null, null, null, null, null);
		
		while(cursor.moveToNext()) {
			Aluno aluno = new Aluno();
			aluno.setId(cursor.getLong(0));
			aluno.setNome(cursor.getString(1));
			aluno.setTelefone(cursor.getString(2));
			aluno.setEndereco(cursor.getString(3));
			aluno.setSite(cursor.getString(4));
			aluno.setNota(cursor.getDouble(5));
			aluno.setFoto(cursor.getString(6));
			alunos.add(aluno);
		}
		cursor.close();
		return alunos;
	}
	
	public void atualiza(Aluno aluno) {
		String[] args = {aluno.getId().toString()};
		ContentValues values = toContentValues(aluno);
		getWritableDatabase().update(TABELA, values, "id=?", args);
	}
	
}
