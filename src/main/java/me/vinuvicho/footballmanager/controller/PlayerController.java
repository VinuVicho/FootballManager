package me.vinuvicho.footballmanager.controller;

import lombok.AllArgsConstructor;
import me.vinuvicho.footballmanager.entity.Player;
import me.vinuvicho.footballmanager.exeption.PlayerNotFoundException;
import me.vinuvicho.footballmanager.exeption.TeamNotFoundException;
import me.vinuvicho.footballmanager.exeption.TooPoorTeam;
import me.vinuvicho.footballmanager.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/all")
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.findAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("playerId") Long id) {
        Player player = null;
        try {
            player = playerService.findPlayerById(id);
        } catch (PlayerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        Player newPlayer = null;
        try {
            newPlayer = playerService.addPlayer(player);
        } catch (TeamNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
        try {
            Player updatedPlayer = playerService.updatePlayer(player);
            return new ResponseEntity<>(updatedPlayer, HttpStatus.CREATED);
        } catch (TooPoorTeam e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        } catch (TeamNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<?> updatePlayer(@PathVariable("playerId") Long id) {
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
