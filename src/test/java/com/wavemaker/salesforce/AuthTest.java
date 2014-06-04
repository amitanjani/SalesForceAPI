package com.wavemaker.salesforce;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.wavemaker.salesforce.util.ResourceLoader;

public class AuthTest {
	
	@Test
	public void testOAuthUsernamePasswordFlow() {
		ForceApi api = new ForceApi(new ApiConfig()
			.setUsername(ResourceLoader.get("username"))
			.setPassword(ResourceLoader.get("password"))
			.setClientId(ResourceLoader.get("clientId"))
			.setClientSecret(ResourceLoader.get("clientSecret")));

		assertNotNull(api.session);
		assertNotNull(api.session.accessToken);
		assertNotNull(api.session.apiEndpoint);
	}
}
