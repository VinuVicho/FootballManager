package me.vinuvicho.footballmanager.controller;

import lombok.AllArgsConstructor;
import me.vinuvicho.footballmanager.dto.PlayerDTO;
import me.vinuvicho.footballmanager.exeption.PlayerValidationFailed;
import me.vinuvicho.footballmanager.exeption.TeamNotFoundException;
import me.vinuvicho.footballmanager.exeption.TooPoorTeam;
import me.vinuvicho.footballmanager.mapper.PlayerToPlayerDTOMapper;
import me.vinuvicho.footballmanager.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;
    private final PlayerToPlayerDTOMapper playerDTOMapper;

    @GetMapping("/all")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        List<PlayerDTO> result = playerService.findAllPlayers()
                .stream().map(PlayerDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable("playerId") Long id) {
        try {
            PlayerDTO player = new PlayerDTO(playerService.findPlayerById(id));
            return new ResponseEntity<>(player, HttpStatus.OK);
        } catch (PlayerValidationFailed e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<PlayerDTO> addPlayer(@RequestBody PlayerDTO player) {
        try {
            PlayerDTO newPlayer = new PlayerDTO(playerService.addPlayer(playerDTOMapper.toEntity(player)));
            return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
        } catch (TeamNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (PlayerValidationFailed | TransactionSystemException playerValidationFailed) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<PlayerDTO> updatePlayer(@RequestBody PlayerDTO player) {
        try {
            PlayerDTO updatedPlayer = new PlayerDTO(playerService.updatePlayer(playerDTOMapper.toEntity(player)));
            return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
        } catch (TooPoorTeam e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (TeamNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (PlayerValidationFailed | TransactionSystemException playerValidationFailed) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<?> updatePlayer(@PathVariable("playerId") Long id) {
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
