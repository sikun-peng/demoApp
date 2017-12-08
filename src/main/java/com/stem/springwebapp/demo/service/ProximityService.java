package com.stem.springwebapp.demo.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stem.springwebapp.demo.model.MapService;
import com.stem.springwebapp.demo.model.Proximity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Service
@SuppressWarnings("unchecked")
@Transactional
public class ProximityService {

    public static final String PROXIMITY = "proximity";
    MapService map_service = new MapService();

    @PersistenceContext
    private EntityManager em;

    public Proximity createProximity(String location_type, int happy_count,  int total_count, int happy_rate) {
    	
    	    Proximity proximity = new Proximity();
    	    proximity.setLocationType(location_type);
    	    proximity.setHappyCount(happy_count);
    	    proximity.setTotalCount(total_count);
    	    proximity.setHappyRate(happy_rate);
     
        em.persist(proximity);
        return proximity;
    }

    public int frequency() {
        String sql = "SELECT COUNT(*) FROM PICTURE p WHERE p.mode = mood)";
        return (int) em.createQuery(sql).getSingleResult();
    }
  
    @Transactional(readOnly = true)
    public List<Proximity> getAllMoods() {
        return em.createQuery("FROM Picture").getResultList();
    }

    @Cacheable(PROXIMITY )
    @Transactional(readOnly = true)
    public Proximity getProximityById(Integer id) {
        return em.find(Proximity.class, id);
    }

    @CacheEvict(PROXIMITY )
    public void deleteProximity(Integer id) {
    		Proximity picture = getProximityById(id);
        em.remove(picture);
    }

    @CachePut(value = PROXIMITY , key = "#id")
    public void updateProximity(Integer id, String location_type, int happy_count,  int total_count, int happy_rate) {
    		Proximity proximity = getProximityById(id);
    	    proximity.setLocationType(location_type);
    	    proximity.setHappyCount(happy_count);
    	    proximity.setTotalCount(total_count);
    	    proximity.setHappyRate(happy_rate);
        em.merge(proximity);
    }
}
