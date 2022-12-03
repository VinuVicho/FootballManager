package me.vinuvicho.footballmanager.repository;

import me.vinuvicho.footballmanager.entity.Player;
import me.vinuvicho.footballmanager.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TeamRepo extends JpaRepository<Team, Long> {

    List<Team> getAllBy();

    Optional<Team> getTeamById(Long id);

    Optional<Team> getTeamByName(String name);
}
