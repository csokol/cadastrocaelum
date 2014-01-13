package br.com.caelum.cadastro.fragment;

import java.io.Serializable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Prova;

public class DetalhesProvaFragment extends Fragment {

	private Prova prova;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.provas_detalhe, container,
				false);
		
		if (getArguments() != null) {
			prova = (Prova) getArguments().getSerializable("prova");
			
			TextView data = (TextView) layout.findViewById(R.id.detalhe_prova_data);
			data.setText(prova.getData());
			
			TextView materia = (TextView) layout.findViewById(R.id.detalhe_prova_materia);
			materia.setText(prova.getMateria());
			
			ListView topicos = (ListView) layout.findViewById(R.id.detalhe_prova_topicos);
			
			ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, prova.getTopicos());
			topicos.setAdapter(adapter);
		}

		return layout;
	}

}
