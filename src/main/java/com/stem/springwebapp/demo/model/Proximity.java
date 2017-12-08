package com.stem.springwebapp.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/*
 * VIEW:  For the proximity use case by user
----------
 
CREATE VIEW public.proximity AS
 SELECT t.userid as id ,
    t.locationtype as location_type,
    t.mood AS happy_count,
    u.mood AS total_count,
    100 * t.mood / u.mood AS happy_rate
   FROM ( SELECT md1.userid,
            count(md1.mood) AS mood,
            md1.locationtype
           FROM mood_distribution md1
          GROUP BY md1.userid, md1.locationtype, md1.mood
         HAVING md1.mood = 'happy'::text) t,
    ( SELECT count(md2.mood) AS mood,
            md2.locationtype
           FROM mood_distribution md2
          GROUP BY md2.userid, md2.locationtype) u
  WHERE t.locationtype = u.locationtype and t.userid=’1’;
  
 */

@Entity
public class Proximity implements Serializable {

	private static final long serialVersionUID = 1500516986755256732L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String location_type;

    private int happy_count;
    
    private int total_count;
    
    private int happy_rate;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getLocationType() {
        return location_type;
    }
    
    public void setLocationType(String location_type){
    		this.location_type = location_type;
    }
    
    public int getHappyCount() {
        return happy_count;
    }

    public void setHappyCount(int happy_count) {
        this.happy_count = happy_count;
    }

    public int getTotalCount() {
        return total_count;
    }

    public void setTotalCount(int total_count) {
        this.total_count = total_count;
    }

    public int getHappyRate() {
        return happy_rate;
    }

    public void setHappyRate(int happy_rate) {
        this.happy_rate = happy_rate;
    } 
    

    
}
