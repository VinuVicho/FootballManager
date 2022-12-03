package me.vinuvicho.footballmanager.mapper;

import lombok.AllArgsConstructor;
import me.vinuvicho.footballmanager.dto.PlayerDTO;
import me.vinuvicho.footballmanager.dto.TeamDTO;
import me.vinuvicho.footballmanager.entity.Player;
import me.vinuvicho.footballmanager.entity.Team;
import me.vinuvicho.footballmanager.exeption.TeamNotFoundException;
import me.vinuvicho.footballmanager.repository.TeamRepo;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TeamToTeamDTOMapper {

    private final TeamRepo teamRepo;

    public Team toEntity(final TeamDTO teamDTO) throws TeamNotFoundException {
        final Team team = new Team();
        if (teamDTO.getId() != null) {
            team.setId(teamDTO.getId());
            try {
                //noinspection OptionalGetWithoutIsPresent
                team.setPlayers(teamRepo.getTeamById(team.getId()).get().getPlayers());
            } catch (Exception e) {
                throw new TeamNotFoundException("Team not found with id " + team.getId());
            }
        }
        team.setName(teamDTO.getName());
        team.setCommission(teamDTO.getCommission());
        team.setMoney(teamDTO.getMoney());
        team.setAbout(teamDTO.getAbout());
        team.setCity(teamDTO.getCity());
        team.setCountry(teamDTO.getCountry());
        team.setLogo(teamDTO.getLogo());
        return team;
    }
}
