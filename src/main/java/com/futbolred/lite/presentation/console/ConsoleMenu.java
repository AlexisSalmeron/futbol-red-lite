package com.futbolred.lite.presentation.console;

import com.futbolred.lite.application.service.MatchService;
import com.futbolred.lite.application.service.PlayerService;
import com.futbolred.lite.application.service.TeamService;
import com.futbolred.lite.domain.model.Match;
import com.futbolred.lite.domain.model.Player;
import com.futbolred.lite.domain.model.Team;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleMenu {

    private final TeamService teamService;
    private final PlayerService playerService;
    private final MatchService matchService;
    private final Scanner scanner;

    public ConsoleMenu(
            TeamService teamService,
            PlayerService playerService,
            MatchService matchService
    ) {
        this.teamService = teamService;
        this.playerService = playerService;
        this.matchService = matchService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int option;

        do {
            printMenu();
            option = readInteger("Elige una opción: ");
            handleOption(option);
        } while (option != 0);

        System.out.println("Gracias por usar Futbol Red Lite.");
    }

    private void printMenu() {
        System.out.println("\n=== Futbol Red Lite ===");
        System.out.println("1. Registro de Equipo");
        System.out.println("2. Listado de Equipos");
        System.out.println("3. Registro de Jugador");
        System.out.println("4. Listado de Jugadores");
        System.out.println("5. Agendar Partido");
        System.out.println("6. Lista de Partidos");
        System.out.println("7. Listar Partidos por Equipo");
        System.out.println("0. Salir");
    }

    private void handleOption(int option) {
        try {
            switch (option) {
                case 1 -> registerTeam();
                case 2 -> listTeams();
                case 3 -> registerPlayer();
                case 4 -> listPlayers();
                case 5 -> createMatch();
                case 6 -> listMatches();
                case 7 -> listMatchesByTeam();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } catch (IllegalArgumentException exception) {
            System.out.println("Validation error: " + exception.getMessage());
        } catch (Exception exception) {
            System.out.println("Unexpected error: " + exception.getMessage());
        }
    }

    private void registerTeam() {
        System.out.println("\n--- Registro de Equipos ---");

        String name = readText("Nombre: ");
        String category = readText("Categoria: ");
        String location = readText("Lugar: ");

        Team team = teamService.createTeam(name, category, location);

        System.out.println("Equipo Registrado:");
        System.out.println(team);
    }

    private void listTeams() {
        System.out.println("\n--- Equipos ---");

        List<Team> teams = teamService.getAllTeams();

        if (teams.isEmpty()) {
            System.out.println("No hay equipos registrados.");
            return;
        }

        for (Team team : teams) {
            System.out.println(team);
        }
    }

    private void registerPlayer() {
        System.out.println("\n--- Registro de Jugadores ---");

        if (teamService.getAllTeams().isEmpty()) {
            System.out.println("Debes registrar al menos un equipo para unir el jugador.");
            return;
        }

        String name = readText("Nombre: ");
        String position = readText("Posición: ");
        Integer number = readInteger("Número: ");

        listTeams();

        Long teamId = readLong("Id Equipo: ");
        Optional<Team> teamOptional = teamService.findTeamById(teamId);

        if (teamOptional.isEmpty()) {
            System.out.println("Equipo No encontrado por ID " + teamId);
            return;
        }

        Player player = playerService.createPlayer(
                name,
                position,
                number,
                teamOptional.get()
        );

        System.out.println("Jugador Registrado:");
        System.out.println(player);
    }

    private void listPlayers() {
        System.out.println("\n--- Jugadores ---");

        List<Player> players = playerService.getAllPlayers();

        if (players.isEmpty()) {
            System.out.println("No hay jugadores registrados");
            return;
        }

        for (Player player : players) {
            System.out.println(player);
        }
    }

    private void createMatch() {
        System.out.println("\n--- Crear partido ---");

        if (teamService.getAllTeams().size() < 2) {
            System.out.println("Debes registrar al menos 2 equipos para hacer un partido");
            return;
        }

        listTeams();

        Long homeTeamId = readLong("ID equipo Local: ");
        Long awayTeamId = readLong("ID equipo visitante: ");

        Optional<Team> homeTeamOptional = teamService.findTeamById(homeTeamId);
        Optional<Team> awayTeamOptional = teamService.findTeamById(awayTeamId);

        if (homeTeamOptional.isEmpty()) {
            System.out.println("No se encontró el equipo local por el id: " + homeTeamId);
            return;
        }

        if (awayTeamOptional.isEmpty()) {
            System.out.println("No se encontró el equipo visitante por Id: " + awayTeamId);
            return;
        }

        Match match = matchService.createMatch(
                homeTeamOptional.get(),
                awayTeamOptional.get()
        );

        System.out.println("Partido Creado con Exito:");
        System.out.println(match);
    }

    private void listMatches() {
        System.out.println("\n--- Partidos ---");

        List<Match> matches = matchService.getAllMatches();

        if (matches.isEmpty()) {
            System.out.println("No hay partidos registrados");
            return;
        }

        for (Match match : matches) {
            System.out.println(match);
        }
    }

    private void listMatchesByTeam() {
        System.out.println("\n--- Partidos por equipo---");

        if (teamService.getAllTeams().isEmpty()) {
            System.out.println("No hay equipos registrados");
            return;
        }

        listTeams();

        Long teamId = readLong("Id de Equipo: ");
        Optional<Team> teamOptional = teamService.findTeamById(teamId);

        if (teamOptional.isEmpty()) {
            System.out.println("Equipo no encontrado por Id " + teamId);
            return;
        }

        List<Match> matches = matchService.getMatchesByTeam(teamOptional.get());

        if (matches.isEmpty()) {
            System.out.println("No se encontraron partidos para este equipo.");
            return;
        }

        for (Match match : matches) {
            System.out.println(match);
        }
    }

    private String readText(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private Integer readInteger(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Número Invalido. Intente de nuevo");
            }
        }
    }

    private Long readLong(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                return Long.parseLong(input);
            } catch (NumberFormatException exception) {
                System.out.println("Id Invalido Intentalo de nuevo");
            }
        }
    }
}