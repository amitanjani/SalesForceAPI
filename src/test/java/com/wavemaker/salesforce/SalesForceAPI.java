package com.wavemaker.salesforce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SalesForceAPI {

	static private ForceApi api = new ForceApi(new ApiConfig()
					.setUsername("vinod.kakarla@imaginea.com")
					.setPassword("Sales@1237JfKq3llawuCX8QuxpZQf4Ar")
					.setClientId("3MVG9Y6d_Btp4xp7uSMhbpgMhnN3Gkp4HXz0QN_OWGLU2ZG6DpzsWOa9oIdxjCBY1TGBBJrZtCYS7ZYnlk1d2")
					.setClientSecret("2113399610016071264"));

	private static Map<String, Object> resultMap = null;
	
	private List<Map> parseResponse(List<Map> records) {
		Map<String, Object> tmpMap = null;
		List<Map> resultList = new ArrayList<Map>();
		for (Map map : records) {
			tmpMap = new HashMap<String, Object>();
			Iterator keyIterator = map.keySet().iterator();
			while (keyIterator.hasNext()) {
				String key = keyIterator.next().toString();
				if (map.get(key) instanceof LinkedHashMap) {
					if (!SFConstants.ATTRIBUTES.equalsIgnoreCase(key)) {
						HashMap childMap = (HashMap) map.get(key);
						if (childMap instanceof LinkedHashMap) {
							Iterator childItr = childMap.keySet().iterator();
							while (childItr.hasNext()) {
								String childKey = childItr.next().toString();
								if (!SFConstants.ATTRIBUTES
										.equalsIgnoreCase(childKey)) {
									tmpMap.put(key + SFConstants.UNDERSCORE + childKey,
											map.get(childKey));
								}
							}
						}
					}
				} else {
					tmpMap.put(key, map.get(key));
				}
			}
			resultList.add(tmpMap);
		}
		return resultList;
	}
	
	private Map<String, Object> executeQuery(String query) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map> resultList = new ArrayList<Map>();
		ForceApi tmpAPI = new ForceApi(api.getSession());
		System.out.println(tmpAPI.query(query).getRecords());
		resultList = parseResponse(tmpAPI.query(query).getRecords());
		resultMap.put("result", resultList);
		return resultMap;
	}
	
	public static void main(String[] args) {
		/*
		 * QueryList
		 * 
		 *  SELECT title, name, Contact.Account.Name, email, phone FROM Contact
		 *	SELECT name, Company, email,status, title, phone FROM Lead
		 *	SELECT name, AccountNumber, AnnualRevenue, Phone, Industry, NumberOfEmployees FROM Account
		 */
		SalesForceAPI api = new SalesForceAPI();
		api.executeQuery("SELECT name FROM Account where name like 'TEST-ACCOUNT-%'");
	}

}
