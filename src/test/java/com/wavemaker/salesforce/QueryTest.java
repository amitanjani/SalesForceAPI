package com.wavemaker.salesforce;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.wavemaker.salesforce.util.ResourceLoader;

public class QueryTest {

	ForceApi api;
	
	@Before
	public void init() {
		api = new ForceApi(new ApiConfig()
		.setUsername(ResourceLoader.get("username"))
		.setPassword(ResourceLoader.get("password")));
	}
	
	@Test
	public void testUntypedQuery() {
		@SuppressWarnings("rawtypes")
		List<Map> result = api.query("SELECT name FROM Account").getRecords();
		// Note, attribute names are capitalized by the Force.com REST API
		assertNotNull(result.get(0).get("Name"));
	}

	@Test
	public void testTypedQuery() {
		List<Account> result = api.query("SELECT name FROM Account",Account.class).getRecords();
		// Note, attribute names are capitalized by the Force.com REST API
		assertNotNull(result.get(0).getName());
	}
}
