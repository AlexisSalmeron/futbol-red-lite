package com.futbolred.lite.domain.model;

public class Team {

    //Atributos
    private Long id;
    private String name;
    private String category;
    private String location;

    //Constructor
    public Team(Long id, String name, String category, String location) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.location = location;
    }

    //Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    //Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //To String
    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}