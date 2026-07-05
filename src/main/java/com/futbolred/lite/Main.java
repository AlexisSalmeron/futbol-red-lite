package com.futbolred.lite;

import com.futbolred.lite.application.service.MatchService;
import com.futbolred.lite.application.service.PlayerService;
import com.futbolred.lite.application.service.TeamService;
import com.futbolred.lite.domain.model.Match;
import com.futbolred.lite.domain.model.Player;
import com.futbolred.lite.domain.model.Team;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Futbol Red Lite ===");

        TeamService teamService = new TeamService();
        PlayerService playerService = new PlayerService();
        MatchService matchService = new MatchService();

        Team cobras = teamService.createTeam("Cobras de Atencingo", "Libre", "Atencingo");
        Team atleticos = teamService.createTeam("Atléticos de San Nicolás", "Libre", "San Nicolás");
        Team rockies = teamService.createTeam("Rockies de Colón", "Libre", "Colón");
        Team seleccionSanJuan = teamService.createTeam("Selección San Juan", "Sub-14", "San Juan");

        System.out.println("Total teams: " + teamService.countTeams());

        playerService.createPlayer("Juan Pérez", "Forward", 9, cobras);
        playerService.createPlayer("Carlos Ramírez", "Goalkeeper", 1, cobras);
        playerService.createPlayer("Luis Hernández", "Midfielder", 10, cobras);

        playerService.createPlayer("Miguel López", "Forward", 11, atleticos);
        playerService.createPlayer("Pedro Sánchez", "Defender", 4, atleticos);
        playerService.createPlayer("Raúl García", "Goalkeeper", 1, atleticos);

        playerService.createPlayer("Diego Martínez", "Forward", 7, rockies);
        playerService.createPlayer("Ángel Torres", "Midfielder", 8, rockies);
        playerService.createPlayer("Jorge Flores", "Defender", 5, rockies);

        playerService.createPlayer("Emiliano Cruz", "Forward", 9, seleccionSanJuan);
        playerService.createPlayer("Mateo Morales", "Midfielder", 10, seleccionSanJuan);
        playerService.createPlayer("Santiago Reyes", "Goalkeeper", 1, seleccionSanJuan);

        System.out.println("Total players: " + playerService.countPlayers());

        matchService.createMatch(cobras, atleticos);
        matchService.createMatch(rockies, seleccionSanJuan);
        matchService.createMatch(cobras, rockies);
        //matchService.createMatch(cobras,cobras);
        //System.out.println("Primer Partido "+matchService.findMatchById(1L).get().toString());

        System.out.println("Total matches: " + matchService.countMatches());

        printTeams(teamService.getAllTeams());
        printPlayers(playerService.getAllPlayers());
        printMatches(matchService.getAllMatches());

        System.out.println("\n--- Players from Cobras de Atencingo ---");
        List<Player> cobrasPlayers = playerService.getPlayersByTeam(cobras);
        for (Player player : cobrasPlayers) {
            System.out.println(player);
        }

        System.out.println("\n--- Matches from Cobras de Atencingo ---");
        List<Match> cobrasMatches = matchService.getMatchesByTeam(cobras);
        for (Match match : cobrasMatches) {
            System.out.println(match);
        }
    }



    private static void printTeams(List<Team> teams) {
        System.out.println("\n--- Teams ---");
        for (Team team : teams) {
            System.out.println(team);
        }
    }

    private static void printPlayers(List<Player> players) {
        System.out.println("\n--- Players ---");
        for (Player player : players) {
            System.out.println(player);
        }
    }

    private static void printMatches(List<Match> matches) {
        System.out.println("\n--- Matches ---");
        for (Match match : matches) {
            System.out.println(match);
        }
    }
}