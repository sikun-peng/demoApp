package com.stem.springwebapp.demo.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MapService {
	private static final Logger logger = LoggerFactory
            .getLogger(MapService.class);
    private static final String mapServiceUrl = "https://mapService.aws-usw02-dev.ice.predix.io/location"; //Not an actual endpoint
    
	  public String getMapService(String latitude, String longitude){ 
	    	RestTemplate restTemplate = new RestTemplate();       
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("latitude", latitude);
	        headers.add("longitude", longitude);
			headers.setContentType(MediaType.APPLICATION_JSON);        
			HttpEntity<String> entity = new HttpEntity<String>("parameters",headers);
	        ResponseEntity<Location> res = restTemplate.exchange(mapServiceUrl, HttpMethod.GET, entity, Location.class);   	
	        logger.info("post calld *****");
	        return res.getBody().getLocationType();  //return String location type "residential", "school", "office", etc
	    }
}
