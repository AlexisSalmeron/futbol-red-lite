package com.futbolred.lite;

import com.futbolred.lite.application.service.MatchService;
import com.futbolred.lite.application.service.PlayerService;
import com.futbolred.lite.application.service.TeamService;
import com.futbolred.lite.presentation.console.ConsoleMenu;

public class Main {

    public static void main(String[] args) {
        TeamService teamService = new TeamService();
        PlayerService playerService = new PlayerService();
        MatchService matchService = new MatchService();

        ConsoleMenu consoleMenu = new ConsoleMenu(
                teamService,
                playerService,
                matchService
        );

        consoleMenu.start();
    }
}