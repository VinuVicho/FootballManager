package me.vinuvicho.footballmanager.service;

import lombok.AllArgsConstructor;
import me.vinuvicho.footballmanager.entity.Player;
import me.vinuvicho.footballmanager.entity.Team;
import me.vinuvicho.footballmanager.exeption.TeamNotFoundException;
import me.vinuvicho.footballmanager.repository.PlayerRepo;
import me.vinuvicho.footballmanager.repository.TeamRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepo teamRepo;
    private final PlayerRepo playerRepo;

    public Team addTeam(Team team) {
        team = validateTeam(team);
        return teamRepo.save(team);
    }

    public Team validateTeam(Team team) {
        if (team.getCommission() < 0) team.setCommission(0L);
        if (team.getCommission() > 10) team.setCommission(10L);
        return team;
    }

    public List<Team> findAllTeams() {
        return teamRepo.getAllBy();
    }

    public Team updateTeam(Team team) {
        team = validateTeam(team);
        team.setPlayers(teamRepo.getTeamById(team.getId()).get().getPlayers());
        return teamRepo.save(team);
    }

    public Team findTeamByName(String name) throws TeamNotFoundException {
        return teamRepo.getTeamByName(name).orElseThrow(() -> new TeamNotFoundException("No Team found"));
    }

    @SuppressWarnings("StringOperationCanBeSimplified")
    public void deleteTeam(Long id) throws TeamNotFoundException {
        Team team = teamRepo.getTeamById(id).orElseThrow(() -> new TeamNotFoundException("team doesnt exist"));
        List<Player> players = team.getPlayers();
        for (Player p: players) p.setTeamName(new String());
        playerRepo.saveAll(players);
        teamRepo.deleteById(id);
    }
}
