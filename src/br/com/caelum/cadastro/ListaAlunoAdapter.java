package br.com.caelum.cadastro;

import java.util.List;

import br.com.caelum.cadastro.modelo.Aluno;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListaAlunoAdapter extends BaseAdapter {
	
	private List<Aluno> alunos;
	private Activity activity;

	public ListaAlunoAdapter(List<Aluno> alunos, Activity activity) {
		this.alunos = alunos;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return alunos.size();
	}

	@Override
	public Object getItem(int pos) {
		return alunos.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return alunos.get(pos).getId();
	}

	@Override
	public View getView(int pos, View arg1, ViewGroup arg2) {
		LayoutInflater inflater = activity.getLayoutInflater();
		View item = inflater.inflate(R.layout.item, null);
		if (pos % 2 == 0) {
			item.setBackgroundColor(activity.getResources().getColor(R.color.linha_par));
		}
		
		Aluno aluno = alunos.get(pos);
		
		setFoto(item, aluno);
		
		TextView nome = (TextView) item.findViewById(R.id.nome);
		nome.setText(aluno.getNome());
		
		TextView telefone = (TextView) item.findViewById(R.id.telefone);
		if (telefone != null) {
			telefone.setText(aluno.getTelefone());
		}
		
		TextView site = (TextView) item.findViewById(R.id.site);
		if (site != null) {
			site.setText(aluno.getSite());
		}
		
		return item;
	}

	private void setFoto(View item, Aluno aluno) {
		ImageView foto = (ImageView) item.findViewById(R.id.foto);
		Bitmap bitmap = null;
		if (aluno.getFoto() != null) {
			bitmap = BitmapFactory.decodeFile(aluno.getFoto());
		} else {
			bitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);
		}
		foto.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 100, 100, true));
	}

}
