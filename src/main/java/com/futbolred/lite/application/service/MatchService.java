package com.futbolred.lite.application.service;

import com.futbolred.lite.domain.model.Match;
import com.futbolred.lite.domain.model.MatchState;
import com.futbolred.lite.domain.model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatchService {

    private final List<Match> matches = new ArrayList<>();
    private Long nextId = 1L;

    public Match createMatch(Team homeTeam, Team awayTeam) {
        validateMatchTeams(homeTeam, awayTeam);

        Match match = new Match(nextId, homeTeam, awayTeam);
        matches.add(match);
        nextId++;

        return match;
    }

    public List<Match> getAllMatches() {
        return new ArrayList<>(matches);
    }

    public List<Match> getScheduledMatches() {
        return matches.stream()
                .filter(match -> match.getState() == MatchState.SCHEDULED)
                .toList();
    }

    public Optional<Match> findMatchById(Long id) {
        if (id == null) {
            return Optional.empty();
        }

        return matches.stream()
                .filter(match -> match.getId().equals(id))
                .findFirst();
    }

    public long countMatches() {
        return matches.size();
    }

    public List<Match> getMatchesByTeam(Team team) {
        if (team == null || team.getId() == null) {
            return new ArrayList<>();
        }

        return matches.stream()
                .filter(match ->
                        match.getHomeTeam().getId().equals(team.getId()) ||
                                match.getAwayTeam().getId().equals(team.getId())
                )
                .toList();
    }


    private void validateMatchTeams(Team homeTeam, Team awayTeam) {
        if (homeTeam == null) {
            throw new IllegalArgumentException("El Equipo Local es Obligatorio");
        }

        if (awayTeam == null) {
            throw new IllegalArgumentException("El equipo visitante es obligatorio");
        }

        if (homeTeam.getId().equals(awayTeam.getId())) {
            throw new IllegalArgumentException("Un equipo no puede jugar contra si mismo");
        }
    }
}