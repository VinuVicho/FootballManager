package me.vinuvicho.footballmanager.repository;

import me.vinuvicho.footballmanager.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PlayerRepo extends JpaRepository<Player, Long> {

    Player getPlayerById(Long id);
    void deletePlayerById(Long id);
}
