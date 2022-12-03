package me.vinuvicho.footballmanager.controller;

import lombok.AllArgsConstructor;
import me.vinuvicho.footballmanager.dto.PlayerDTO;
import me.vinuvicho.footballmanager.dto.TeamDTO;
import me.vinuvicho.footballmanager.entity.Player;
import me.vinuvicho.footballmanager.entity.Team;
import me.vinuvicho.footballmanager.exeption.TeamNotFoundException;
import me.vinuvicho.footballmanager.exeption.TeamValidationFailed;
import me.vinuvicho.footballmanager.mapper.TeamToTeamDTOMapper;
import me.vinuvicho.footballmanager.service.PlayerService;
import me.vinuvicho.footballmanager.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;
    private final TeamToTeamDTOMapper teamDTOMapper;
    private PlayerService playerService;

    @GetMapping("/all")
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        List<TeamDTO> teams = teamService.findAllTeams()
                .stream().map(TeamDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/{teamName}")
    public ResponseEntity<TeamDTO> getTeamByName(@PathVariable("teamName") String name) {
        try {
            Team team = teamService.findTeamByName(name);
            return new ResponseEntity<>(new TeamDTO(team), HttpStatus.OK);
        } catch (TeamNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{teamId}/players")            //not using
    public ResponseEntity<List<PlayerDTO>> getTeamPlayers(@PathVariable("teamId") Long id) {
        try {
            List<PlayerDTO> players = playerService.getTeamPlayers(id)
                    .stream().map(PlayerDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(players, HttpStatus.OK);
        } catch (TeamNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<TeamDTO> addTeam(@RequestBody TeamDTO team) {
        try {
            Team newTeam = teamService.addTeam(teamDTOMapper.toEntity(team));
            return new ResponseEntity<>(new TeamDTO(newTeam), HttpStatus.CREATED);
        } catch (TeamNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (TeamValidationFailed | TransactionSystemException teamValidationFailed) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<TeamDTO> updateTeam(@RequestBody TeamDTO team) {
        try {
            Team updateTeam = teamService.updateTeam(teamDTOMapper.toEntity(team));
            return new ResponseEntity<>(new TeamDTO(updateTeam), HttpStatus.OK);
        } catch (TeamNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (TeamValidationFailed | TransactionSystemException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable("teamId") Long id) {
        try {
            teamService.deleteTeam(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (TeamNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
