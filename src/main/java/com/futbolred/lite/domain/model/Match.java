package com.futbolred.lite.domain.model;

public class Match {

    private Long id;
    private Team homeTeam;
    private Team awayTeam;
    private Integer homeGoals;
    private Integer awayGoals;
    private MatchState state;

    public Match(Long id, Team homeTeam, Team awayTeam) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = null;
        this.awayGoals = null;
        this.state = MatchState.SCHEDULED;
    }

    public Long getId() {
        return id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Integer getHomeGoals() {
        return homeGoals;
    }

    public Integer getAwayGoals() {
        return awayGoals;
    }

    public MatchState getState() {
        return state;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setHomeGoals(Integer homeGoals) {
        this.homeGoals = homeGoals;
    }

    public void setAwayGoals(Integer awayGoals) {
        this.awayGoals = awayGoals;
    }

    public void setState(MatchState state) {
        this.state = state;
    }

    public boolean hasResult() {
        return homeGoals != null && awayGoals != null;
    }

    @Override
    public String toString() {
        String score = hasResult()
                ? homeGoals + " - " + awayGoals
                : "No result";

        return "Match{" +
                "id=" + id +
                ", homeTeam=" + homeTeam.getName() +
                ", awayTeam=" + awayTeam.getName() +
                ", score='" + score + '\'' +
                ", state=" + state +
                '}';
    }
}