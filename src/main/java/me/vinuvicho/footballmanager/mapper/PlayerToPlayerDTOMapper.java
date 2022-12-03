package me.vinuvicho.footballmanager.mapper;

import lombok.AllArgsConstructor;
import me.vinuvicho.footballmanager.dto.PlayerDTO;
import me.vinuvicho.footballmanager.entity.Player;
import me.vinuvicho.footballmanager.exeption.TeamNotFoundException;
import me.vinuvicho.footballmanager.repository.TeamRepo;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlayerToPlayerDTOMapper {

    private final TeamRepo teamRepo;

    public Player toEntity (final PlayerDTO playerDTO) throws TeamNotFoundException {
        final Player player = new Player();
        final String teamName = playerDTO.getTeamName();
        if (teamName != null) {
            if (!teamName.equals(""))
                player.setTeam(teamRepo.getTeamByName(teamName)
                        .orElseThrow(() -> new TeamNotFoundException("There no such team")));
            else player.setTeam(null);
        }
        player.setPlayerName(playerDTO.getPlayerName());
        player.setBio(playerDTO.getBio());
        player.setBirthDate(playerDTO.getBirthDate());
        player.setCareerStarted(playerDTO.getCareerStarted());
        player.setSurname(playerDTO.getSurname());
        player.setId(playerDTO.getId());
        player.setPhotoUrl(playerDTO.getPhotoUrl());

        return player;
    }

}
