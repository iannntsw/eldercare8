package com.example.eldercare.DayPlanner;

public class MyDoes {

    String titledoes;
    String descdoes;
    String datedoes;
    String doesId;

    public MyDoes() {

    }

    public MyDoes(String titledoes, String descdoes, String datedoes, String doesId) {
        this.titledoes = titledoes;
        this.descdoes = descdoes;
        this.datedoes = datedoes;
        this.doesId = doesId;
    }

    public String getDoesId() {
        return doesId;
    }

    public void setDoesId(String doesId) {
        this.doesId = doesId;
    }

    public String getTitledoes() {
        return titledoes;
    }

    public void setTitledoes(String titledoes) {
        this.titledoes = titledoes;
    }

    public String getDescdoes() {
        return descdoes;
    }

    public void setDescdoes(String descdoes) {
        this.descdoes = descdoes;
    }

    public String getDatedoes() {
        return datedoes;
    }

    public void setDatedoes(String datedoes) {
        this.datedoes = datedoes;
    }
}
