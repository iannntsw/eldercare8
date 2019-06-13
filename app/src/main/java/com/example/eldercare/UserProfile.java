package com.example.eldercare;

public class UserProfile {
    public String email;
    public String name;
    public String age;
    public String weight;
    public String diet;
    public String conditions;

    public UserProfile(String email, String name, String age, String weight, String diet, String conditions) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.diet = diet;
        this.conditions = conditions;
    }
}
