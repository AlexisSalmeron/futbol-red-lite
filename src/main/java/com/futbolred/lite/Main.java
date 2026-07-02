package com.futbolred.lite;

import com.futbolred.lite.domain.model.*;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Futbol Red Lite ===");

        Team union = new Team(1L, "Union Tilapa", "Sabatina", "Tilapa");
        //System.out.println(team);
        Team cuervos = new Team(2L, "Cuervos", "Sabatina", "Izúcar");
        //System.out.println(team1);

        //Listado de Equipos
        List<Team> teams = new ArrayList<>();
        teams.add(union);
        teams.add(cuervos);

        Player player1 = new Player(1L, "Alexis Salmeron", "Delantero", 10, union);
        //System.out.println("player = " + player);
        Player player2 = new Player(2L, "David Pardo", "Defensa", 4, cuervos);
        //System.out.println("player = " + player1);
        Player player3 = new Player(3L, "Jaziel Beltrán", "Delantero", 11, union);

        //Listado de Jugadores
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        //Crear Partido
        Match match = new Match(1L, union, cuervos);

        //Lista de partidos
        List <Match> matches = new ArrayList<>();
        matches.add(match);

        System.out.println("\n--- Teams ---");
        for (Team team : teams) {
            System.out.println(team.getName());
        }

        System.out.println("\n--- Players ---");
        for (Player player : players) {
            System.out.println(player.getName() + " " +player.getNumber() + " " + player.getTeam().getName());
        }

        match.setHomeGoals(2);
        match.setAwayGoals(0);
        match.setState(MatchState.FINISHED);

        System.out.println("\n--- Matches ---");
        for (Match currentMatch : matches) {
            System.out.println(currentMatch);
        }





    }
}