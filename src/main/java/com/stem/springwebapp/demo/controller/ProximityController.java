package com.stem.springwebapp.demo.controller;


import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Collection<Proximity>> getAllproximity() throws Exception {
        Collection<Proximity> proximity = proximityService.getAllProximities();
        return new ResponseEntity<Collection<Proximity>> (proximity, HttpStatus.FOUND);
    }
    
    @RequestMapping(value = PROXIMITY, method = RequestMethod.GET)
    public ResponseEntity<Collection<Proximity>> getUserproximity(@RequestParam String user_id) throws Exception {
        Collection<Proximity> proximity = proximityService.getUserProximities(user_id);
        return new ResponseEntity<Collection<Proximity>>(proximity, HttpStatus.FOUND);
    }

}


 