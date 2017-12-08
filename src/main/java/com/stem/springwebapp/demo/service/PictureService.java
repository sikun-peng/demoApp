package com.stem.springwebapp.demo.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.stem.springwebapp.demo.model.Location;
import com.stem.springwebapp.demo.model.Mood;
import com.stem.springwebapp.demo.model.Picture;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Service
@SuppressWarnings("unchecked")
@Transactional
public class PictureService {
	
    private static final String mapServiceUrl = "https://mapService.aws-usw02-dev.ice.predix.io/location"; //Not an actual endpoint
    public static final String PICTURES = "pictures";
   
    @PersistenceContext
    private EntityManager em;

    public Picture createPicture(Mood mood, String user_id,  Double latitude, Double longitude) {
  	
        Picture picture = new Picture();
        picture.setMood(mood);
        picture.setLatitude(latitude);
        picture.setLongitude(longitude);
        
        String location_type = getMapService(Double.toString(latitude), Double.toString(longitude));
        picture.setLocationType(location_type);
        em.persist(picture);
        return picture;
    }

    public int getAllFrequency() {
        String sql = "select  md.mood, count(mood) as moodcount from mood_distribution md group by mood";
        return (int) em.createNativeQuery(sql).getSingleResult();
    }
    
    public int getUserFrequency(String user_id) {
        String sql = "select md.userid, md.mood, count(mood) as moodcount from mood_distribution md group by mood, userid having userid = user_id";
        return (int) em.createNativeQuery(sql).getSingleResult();
    }
  
    @Transactional(readOnly = true)
    public List<Picture> getAllPictures() {
        return em.createQuery("FROM Picture").getResultList();
    }

    @Cacheable(PICTURES)
    @Transactional(readOnly = true)
    public Picture getPictureById(Integer id) {
        return em.find(Picture.class, id);
    }

    @CacheEvict(PICTURES)
    public void deletePicture(Integer id) {
        Picture picture = getPictureById(id);
        em.remove(picture);
    }

    @CachePut(value = PICTURES, key = "#id")
    public void updatePicture(Integer id, Mood mood, String user_id, Double latitude, Double longitude) {
    		Picture picture = getPictureById(id);
        picture.setMood(mood);
        picture.setUserId(user_id);
        picture.setLatitude(latitude);
        picture.setLongitude(longitude);
        String location_type = getMapService(Double.toString(latitude), Double.toString(longitude)); // calling third party map service to get location type
        picture.setLocationType(location_type);
        em.merge(picture);
    }

    public String getMapService(String latitude, String longitude){ 
    	RestTemplate restTemplate = new RestTemplate();       
        HttpHeaders headers = new HttpHeaders();
        headers.add("latitude", latitude);
        headers.add("longitude", longitude);
		headers.setContentType(MediaType.APPLICATION_JSON);        
		HttpEntity<String> entity = new HttpEntity<String>("parameters",headers);
        ResponseEntity<Location> res = restTemplate.exchange(mapServiceUrl, HttpMethod.GET, entity, Location.class);   	
        return res.getBody().getLocationType();  //return String location type "residential", "school", "office", etc
    }        
}
