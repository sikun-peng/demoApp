package com.stem.springwebapp.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


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
