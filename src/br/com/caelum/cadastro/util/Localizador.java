package br.com.caelum.cadastro.util;

import java.io.IOException;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

public class Localizador {

	private Geocoder geocoder;

	public Localizador(Context context) {
		geocoder = new Geocoder(context);
	}
	
	public LatLng getCoordenada(String endereco) {
		try {
			List<Address> locations = geocoder.getFromLocationName(endereco, 1);
			if (locations.isEmpty()) {
				return null;
			} else {
				Address address = locations.get(0);
				return new LatLng(address.getLatitude(), address.getLongitude());
			}
		} catch (IOException e) {
			return null;
		}
	}
	
	
}
