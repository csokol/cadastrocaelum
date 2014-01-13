package br.com.caelum.cadastro.fragment;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Prova;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class ProvasActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.provas);

		if (bundle == null) {
			FragmentTransaction tx = getSupportFragmentManager()
					.beginTransaction();
			
			if (isTablet()) {
				tx.replace(R.id.provas_view, new DetalhesProvaFragment(),
						DetalhesProvaFragment.class.getSimpleName());
				tx.replace(R.id.provas_lista, new ListaProvasFragment(),
						ListaProvasFragment.class.getSimpleName());
			} else {
				tx.replace(R.id.provas_view, new ListaProvasFragment(),
						"listaProvas");
			}

			tx.commit();
		}
	}

	public void selecionaProva(Prova prova) {
		FragmentTransaction tx = getSupportFragmentManager()
				.beginTransaction();
		DetalhesProvaFragment fragment = new DetalhesProvaFragment();
		
		Bundle bundle = new Bundle();
		bundle.putSerializable("prova", prova);
		fragment.setArguments(bundle);
		
		if (!isTablet()) {
			tx.addToBackStack(null);
		}
		tx.replace(R.id.provas_view, fragment, fragment.getClass().getCanonicalName());
		tx.commit();
	}

	public boolean isTablet() {
		return getResources().getBoolean(R.bool.isTablet);
	}


}
