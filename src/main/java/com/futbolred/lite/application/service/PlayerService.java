package com.futbolred.lite.application.service;

import com.futbolred.lite.domain.model.Player;
import com.futbolred.lite.domain.model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerService {

    private final List<Player> players = new ArrayList<>();
    private Long nextId = 1L;

    public Player createPlayer(String name, String position, Integer number, Team team) {
        validatePlayerData(name, position, number, team);

        Player player = new Player(nextId, name.trim(), position.trim(), number, team);
        players.add(player);
        nextId++;

        return player;
    }

    public List<Player> getAllPlayers() {
        return new ArrayList<>(players);
    }

    public List<Player> getPlayersByTeam(Team team) {
        if (team == null || team.getId() == null) {
            return new ArrayList<>();
        }

        return players.stream()
                .filter(player -> player.getTeam().getId().equals(team.getId()))
                .toList();
    }

    public Optional<Player> findPlayerById(Long id) {
        if (id == null) {
            return Optional.empty();
        }

        return players.stream()
                .filter(player -> player.getId().equals(id))
                .findFirst();
    }

    public long countPlayers() {
        return players.size();
    }

    private void validatePlayerData(String name, String position, Integer number, Team team) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }

        if (position == null || position.isBlank()) {
            throw new IllegalArgumentException("Posicion es requerida");
        }

        if (number == null) {
            throw new IllegalArgumentException("Número es requerido");
        }

        if (number <= 0) {
            throw new IllegalArgumentException("El número debe ser mayor a 0");
        }

        if (team == null) {
            throw new IllegalArgumentException("El equipo es obligatorio");
        }
    }
}