package com.stem.springwebapp.demo.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stem.springwebapp.demo.model.MapService;
import com.stem.springwebapp.demo.model.Mood;
import com.stem.springwebapp.demo.model.Picture;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Service
@SuppressWarnings("unchecked")
@Transactional
public class PictureService {

    public static final String PICTURES = "pictures";
    MapService map_service = new MapService();

    @PersistenceContext
    private EntityManager em;

    public Picture createPicture(Mood mood, String user_id,  Double latitude, Double longitude) {
  	
        Picture picture = new Picture();
        picture.setMood(mood);
        picture.setLatitude(latitude);
        picture.setLongitude(longitude);
        
        String location_type = map_service.getMapService(Double.toString(latitude), Double.toString(longitude));
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
        String location_type = map_service.getMapService(Double.toString(latitude), Double.toString(longitude)); // calling third party map service to get location type
        picture.setLocationType(location_type);
        em.merge(picture);
    }
}
