/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.instagram.config.xml;

import org.springframework.social.config.xml.AbstractProviderConfigBeanDefinitionParser;
import org.springframework.social.security.provider.SocialAuthenticationService;
import org.springframework.social.instagram.config.support.InstagramApiHelper;
import org.springframework.social.instagram.connect.InstagramConnectionFactory;
import org.springframework.social.instagram.security.InstagramAuthenticationService;

/**
 * Implementation of {@link AbstractConnectionFactoryBeanDefinitionParser} that creates a {@link InstagramConnectionFactory}.
 * @author Scott Murphy
 * @author Craig Walls
 */
class InstagramConfigBeanDefinitionParser extends AbstractProviderConfigBeanDefinitionParser {

	public InstagramConfigBeanDefinitionParser() {
		super(InstagramConnectionFactory.class, InstagramApiHelper.class);
	}

	@Override
	protected Class<? extends SocialAuthenticationService<?>> getAuthenticationServiceClass() {
		return InstagramAuthenticationService.class;
	}

}
