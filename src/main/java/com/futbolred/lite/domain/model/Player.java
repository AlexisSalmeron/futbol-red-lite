package com.futbolred.lite.domain.model;

public class Player {
    //Atributos
    private Long id;
    private String name;
    private String position;
    private Integer number;
    private Team team;

    public Player(Long id, String name, String position, Integer number, Team team) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.number = number;
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public Integer getNumber() {
        return number;
    }

    public Team getTeam() {
        return team;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        String teamName = team != null ? team.getName() : "No team";

        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", number=" + number +
                ", team='" + teamName + '\'' +
                '}';
    }
}