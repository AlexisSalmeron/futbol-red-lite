package com.futbolred.lite.application.service;

import com.futbolred.lite.domain.model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamService {

    private final List<Team> teams = new ArrayList<>();
    private Long nextId = 1L;

    //Logica de crear un equipo
    //Recine nombre, categoria, y lugar
    public Team createTeam(String name, String category, String location) {
        validateTeamData(name, category, location);
        Team team = new Team(nextId, name.trim(), category.trim(), location.trim());
        teams.add(team);
        nextId++;

        return team;
    }

    //Obtener equipos
    public List<Team> getAllTeams() {
        return new ArrayList<>(teams);
    }

    //Buscar equipo por Id
    public Optional<Team> findTeamById(Long id) {
        if (id == null) {
            return Optional.empty();
        }

        return teams.stream()
                .filter(team -> team.getId().equals(id))
                .findFirst();
    }

    public long countTeams() {
        return teams.size();
    }

    public boolean existsByName(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }

        return teams.stream()
                .anyMatch(team -> team.getName().equalsIgnoreCase(name.trim()));
    }

    private void validateTeamData(String name, String category, String location) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nombre Obligatorio");
        }

        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Categoria Obligatoria");
        }

        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Lugar Obligatorio");
        }

        if (existsByName(name)) {
            throw new IllegalArgumentException("Este Equipo ya Existe: " + name);
        }
    }
}