package com.example.eldercare.HealthMonitor;

import android.widget.ScrollView;

public class MyHealth {

    String dateHealth;
    String systolicHealth;
    String heartHealth;
    String mapHealth;
    String weightHealth;
    String healthId;

    public MyHealth(){

    }

    public MyHealth(String dateHealth, String systolicHealth, String heartHealth, String mapHealth, String weightHealth, String healthId){
        this.dateHealth = dateHealth;
        this.systolicHealth = systolicHealth;
        this.heartHealth = heartHealth;
        this.mapHealth = mapHealth;
        this.weightHealth = weightHealth;
        this.healthId = healthId;
    }

    public String getHealthId() {
        return healthId;
    }

    public void setHealthId(String healthId) {
        this.healthId = healthId;
    }

    public String getDateHealth() {
        return dateHealth;
    }

    public void setDateHealth(String dateHealth) {
        this.dateHealth = dateHealth;
    }

    public String getSystolicHealth() {
        return systolicHealth;
    }

    public void setSystolicHealth(String systolicHealth) {
        this.systolicHealth = systolicHealth;
    }

    public String getHeartHealth() {
        return heartHealth;
    }

    public void setHeartHealth(String heartHealth) {
        this.heartHealth = heartHealth;
    }

    public String getMapHealth() {
        return mapHealth;
    }

    public void setMapHealth(String mapHealth) {
        this.mapHealth = mapHealth;
    }

    public String getWeightHealth() {
        return weightHealth;
    }

    public void setWeightHealth(String weightHealth) {
        this.weightHealth = weightHealth;
    }
}
