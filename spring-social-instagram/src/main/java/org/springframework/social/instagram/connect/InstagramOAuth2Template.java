package org.springframework.social.instagram.connect;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class InstagramOAuth2Template extends OAuth2Template {

	public InstagramOAuth2Template(String clientId, String clientSecret) {
		super(clientId, clientSecret, "https://api.instagram.com/oauth/authorize", "https://api.instagram.com/oauth/access_token");
        setUseParametersForClientAuthentication(true);
	}
	
	@Override
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = new RestTemplate(ClientHttpRequestFactorySelector.getRequestFactory());
		return restTemplate;
	}
	
	@Override
	@SuppressWarnings("unchecked")	
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
		// TODO: Look into weird JSON response bug.
		String jsonString = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> response2 = null;
		try {
			response2 = mapper.readValue(jsonString, Map.class);
		} catch (Exception e) {
			
		}
		String accessToken = response2.get("access_token");
		return new AccessGrant(accessToken, null, null, null);
	}
}
