package br.com.caelum.cadastro.fragment;

import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Prova;

public class ListaProvasFragment extends Fragment {
	
	private ListView listViewProvas;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layoutProvas = inflater.inflate(R.layout.provas_lista, container, false);
		
		listViewProvas = (ListView) layoutProvas.findViewById(R.id.lista_provas);
		
		List<Prova> provas = Arrays.asList(new Prova("07/02/2013", "Java"), new Prova("07/02/2014", "Android"));
		ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(getActivity(), android.R.layout.simple_list_item_1, provas);
		listViewProvas.setAdapter(adapter);
		
		listViewProvas.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int pos,
					long id) {
				ProvasActivity provasActivity = (ProvasActivity) ListaProvasFragment.this.getActivity();
				
				Prova prova = (Prova) adapter.getItemAtPosition(pos);
				provasActivity.selecionaProva(prova);
			}
		});
		
		return layoutProvas;
	}

}
