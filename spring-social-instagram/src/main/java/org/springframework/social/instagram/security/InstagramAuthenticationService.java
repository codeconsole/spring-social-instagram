package org.springframework.social.instagram.security;

import org.springframework.social.security.provider.OAuth2AuthenticationService;
import org.springframework.social.instagram.api.Instagram;
import org.springframework.social.instagram.connect.InstagramConnectionFactory;

public class InstagramAuthenticationService extends OAuth2AuthenticationService<Instagram> {

	public InstagramAuthenticationService(String apiKey, String appSecret) {
		super(new InstagramConnectionFactory(apiKey, appSecret));
	}

}
