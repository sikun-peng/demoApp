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
    public int getAllFrequency() throws Exception {
        return pictureService.getAllFrequency();
    }

    @RequestMapping(value = PICTURES_FREQUENCY, method = RequestMethod.GET)
    public int getUserFrequency(@RequestParam String user_id) throws Exception {
        return pictureService.getUserFrequency(user_id);
    }
    
    @RequestMapping(value = GET_PICTURES_BY_ID, method = RequestMethod.GET)
    public Picture pictureById(@PathVariable  Integer id) {
        return this.pictureService.getPictureById(id);
    }
    
    @RequestMapping(value = PICTURES, method = RequestMethod.GET)
    public Collection<Picture> picture() throws Exception {
        Collection<Picture> customers = pictureService.getAllPictures();
        return customers;
    }

    @RequestMapping(value = PICTURES, method = RequestMethod.POST)
    public Integer addPicture(@RequestParam Mood mood, @RequestParam String user_id, @RequestParam Double longitude,  @RequestParam Double latitude ) {
    		LOGGER.info("add cutomers");
    		return pictureService.createPicture(mood, user_id, latitude, longitude).getId();
    }
}


 