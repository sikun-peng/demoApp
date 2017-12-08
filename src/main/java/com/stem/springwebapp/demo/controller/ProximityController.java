package com.stem.springwebapp.demo.controller;


import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stem.springwebapp.demo.model.Proximity;
import com.stem.springwebapp.demo.service.ProximityService;

//@API
@ComponentScan
@RestController
@RequestMapping(value = { "/V1"})
public class ProximityController {

    @Autowired  private ProximityService proximityService;

    public static final String PROXIMITY = "/proximity";
    public static final String LOCATIONS = "/locations";

    public static final String PICTURES_FREQUENCY = PROXIMITY + "/frequency";
    public static final String GET_PICTURES_BY_ID = PROXIMITY + "/{id}";

    private static final Logger LOGGER = LoggerFactory.getLogger(ProximityController.class);
    
    
    @RequestMapping(value = PROXIMITY, method = RequestMethod.GET)
    public Collection<Proximity> proximity() throws Exception {
        Collection<Proximity> customers = proximityService.getAllProximities();
        return customers;
    }

    @RequestMapping(value = PROXIMITY, method = RequestMethod.POST)
    public Integer addPicture(@RequestParam String location_type, @RequestParam int happy_count, @RequestParam int total_count,  @RequestParam  int happy_rate ) {
    		LOGGER.info("add proximity");
    		return proximityService.createProximity(location_type, happy_count,  total_count, happy_rate).getId();
    }
}


 