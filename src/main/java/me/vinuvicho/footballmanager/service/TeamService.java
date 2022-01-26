package me.vinuvicho.footballmanager.service;

import lombok.AllArgsConstructor;
import me.vinuvicho.footballmanager.entity.Player;
import me.vinuvicho.footballmanager.entity.Team;
import me.vinuvicho.footballmanager.exeption.TeamNotFoundException;
import me.vinuvicho.footballmanager.repository.TeamRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepo teamRepo;

    public Team addTeam(Team team) {
        //TODO: validate
        return teamRepo.save(team);
    }

    public List<Team> findAllTeams() {
        return teamRepo.getAllBy();
    }

    public Team updateTeam(Team team) {         //prob move to addTeam method, bc of same code
        return teamRepo.save(team);
    }

    public Team findTeamById(Long id) {
        return teamRepo.getTeamById(id).orElseThrow(() -> new TeamNotFoundException("No Team found"));
    }

    public void deleteTeam(Long id) {
        teamRepo.deleteById(id);
    }
}
