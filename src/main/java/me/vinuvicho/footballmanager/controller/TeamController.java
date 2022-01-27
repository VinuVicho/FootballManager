package me.vinuvicho.footballmanager.controller;

import lombok.AllArgsConstructor;
import me.vinuvicho.footballmanager.entity.Player;
import me.vinuvicho.footballmanager.entity.Team;
import me.vinuvicho.footballmanager.service.PlayerService;
import me.vinuvicho.footballmanager.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;
    private PlayerService playerService;

    @GetMapping("/all")
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.findAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable("teamId") Long id) {
        Team team = teamService.findTeamById(id);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @GetMapping("/{teamId}/players")            //not using
    public ResponseEntity<List<Player>> getTeamPlayers(@PathVariable("teamId") Long id) {
        List<Player> players = playerService.getTeamPlayers(id);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Team> addTeam(@RequestBody Team team) {
        Team newTeam = teamService.addTeam(team);
        return new ResponseEntity<>(newTeam, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
        Team updateTeam = teamService.updateTeam(team);
        return new ResponseEntity<>(updateTeam, HttpStatus.CREATED);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<?> updateTeam(@PathVariable("teamId") Long id) {
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
