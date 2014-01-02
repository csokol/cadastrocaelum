package br.com.caelum.support;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class WebClient {

	private final String url;

	public WebClient(String url) {
		this.url = url;
	}
	
	public String post(String json) {
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			
			post.setEntity(new StringEntity(json));
			
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-type", "application/json");
			HttpResponse response = client.execute(post);
			return EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
