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



@ComponentScan
@RestController
@RequestMapping(value = { "/V1"})
public class PictureController {

    @Autowired  private PictureService pictureService;

    public static final String MOODS = "/moods";
    public static final String LOCATIONS = "/locations";

    public static final String MOOD_FREQUENCY = MOODS + "/frequency";
    public static final String GET_MOODS_BY_ID = MOODS + "/{id}";

    private static final Logger LOGGER = LoggerFactory.getLogger(PictureController.class);
    
    @RequestMapping(value = MOOD_FREQUENCY, method = RequestMethod.GET)
    public int getFrequency() throws Exception {
        return pictureService.frequency();
    }

    @RequestMapping(value = GET_MOODS_BY_ID, method = RequestMethod.GET)
    public Picture pictureById(@PathVariable  Integer id) {
        return this.pictureService.getPictureById(id);
    }
    
    @RequestMapping(value = MOODS, method = RequestMethod.GET)
    public Collection<Picture> picture() throws Exception {
        Collection<Picture> customers = pictureService.getAllMoods();
        return customers;
    }

    @RequestMapping(value = MOODS, method = RequestMethod.POST)
    public Integer addPicture(@RequestParam Mood mood, @RequestParam String user_id, @RequestParam Double longitude,  @RequestParam Double latitude ) {
    		LOGGER.info("add cutomers");
    		return pictureService.createPicture(mood, user_id, latitude, longitude).getId();
    }
}


 