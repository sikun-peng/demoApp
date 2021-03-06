package com.stem.springwebapp.demo.controller;


import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stem.springwebapp.demo.handler.InvalidInputException;
import com.stem.springwebapp.demo.model.Mood;
import com.stem.springwebapp.demo.model.Picture;
import com.stem.springwebapp.demo.service.PictureService;

//@API
@ComponentScan
@RestController
@RequestMapping(value = { "/V1"})
public class PictureController {

    @Autowired  private PictureService pictureService;

    public static final String PICTURES = "/pictures";
    public static final String LOCATIONS = "/locations";

    public static final String PICTURES_FREQUENCY = PICTURES + "/frequency";
    public static final String GET_PICTURES_BY_ID = PICTURES + "/{id}";

    private static final Logger LOGGER = LoggerFactory.getLogger(PictureController.class);
    
    @RequestMapping(value = PICTURES_FREQUENCY, method = RequestMethod.GET)
    public ResponseEntity<Integer> getAllFrequency() throws Exception {
        return new ResponseEntity<Integer>(pictureService.getAllFrequency(),HttpStatus.FOUND);
    }

    @RequestMapping(value = PICTURES_FREQUENCY, method = RequestMethod.GET)
    @ExceptionHandler({InvalidInputException.class})
    public ResponseEntity<Integer> getUserFrequency(@RequestParam String user_id) throws Exception {
    	 	if(user_id.length() > 100 ){
             throw new InvalidInputException("user_id invalid");
          } 
        return new ResponseEntity<Integer>(pictureService.getUserFrequency(user_id),HttpStatus.FOUND);
    }
    
    @RequestMapping(value = GET_PICTURES_BY_ID, method = RequestMethod.GET)
    @ExceptionHandler({InvalidInputException.class})
    public ResponseEntity<Picture> getPictureById(@PathVariable  Integer id) {
	 	if(id <= 0 ){
            throw new InvalidInputException("id can not be negative");
         } 
        return new ResponseEntity<Picture> (this.pictureService.getPictureById(id), HttpStatus.FOUND);
    }
    
    @RequestMapping(value = PICTURES, method = RequestMethod.GET)
    public ResponseEntity<Collection<Picture>> getAllPictures() throws Exception {
        Collection<Picture> pictures = pictureService.getAllPictures();
        return new ResponseEntity<Collection<Picture>>(pictures, HttpStatus.FOUND);
    }

    @RequestMapping(value = PICTURES, method = RequestMethod.POST)
    @ExceptionHandler({InvalidInputException.class})
    public ResponseEntity<Integer> addPicture(@RequestParam Mood mood, @RequestParam String user_id, @RequestParam Double longitude,  @RequestParam Double latitude ) {
	 	if(user_id.length() >100){
            throw new InvalidInputException("user_id invalid");
         } 	
	 	else if(latitude >180 || latitude < -180){
	 		throw new InvalidInputException("invalid latitude value");
	 	}
	 	else if(longitude >180 || longitude < -180){
	 		throw new InvalidInputException("invalid longitude value");
	 	}
	 	LOGGER.info("add cutomers");
    		return new ResponseEntity<Integer>(pictureService.createPicture(mood, user_id, latitude, longitude).getId(), HttpStatus.CREATED);
    }
}


 