package me.vinuvicho.footballmanager.exeption;

import org.springframework.data.crossstore.ChangeSetPersister;

public class PlayerNotFoundException extends /*RuntimeException*/ ChangeSetPersister.NotFoundException {
    public PlayerNotFoundException(String e) {

    }
}
