package com.excilys.computerdatabase.ui;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

public class ComputerLineInterface {

	public static final String REST_SERVICE_URI = "http://localhost:8080/computer-database-webservice/";

	public static void main(String[] args) {
		 System.out.println("Testing listAllUsers API-----------");
         
	        RestTemplate restTemplate = new RestTemplate();
	        @SuppressWarnings("unchecked")
			List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI + "dashboard", List.class);
	         
	        if(usersMap!=null){
	            for(LinkedHashMap<String, Object> map : usersMap){
	            	System.out.println(map);
	            }
	        }else{
	            System.out.println("No user exist----------");
	        }
		
	}
}
