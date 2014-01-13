package br.com.caelum.cadastro.fragment;

import java.util.List;

import br.com.caelum.cadastro.dao.AlunoDao;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastro.util.Localizador;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragment extends SupportMapFragment {
	
	@Override
	public void onResume() {
		super.onResume();
		Localizador localizador = new Localizador(getActivity());
		LatLng local = localizador.getCoordenada("Rua Vergueiro 3185 Vila Mariana");
		this.centralizaNo(local);
		
		AlunoDao dao = new AlunoDao(getActivity());
		List<Aluno> alunos = dao.getLista();
		for (Aluno aluno : alunos) {
			LatLng coordenada = localizador.getCoordenada(aluno.getEndereco());
			if (coordenada != null) {
				MarkerOptions marker = new MarkerOptions();
				marker.position(coordenada);
				getMap().addMarker(marker);
			}
		}
		dao.close();
		
	}

	private void centralizaNo(LatLng local) {
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(local, 17);
		getMap().moveCamera(update);
	}

}
