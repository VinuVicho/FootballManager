package me.vinuvicho.footballmanager.exeption;

import org.springframework.data.crossstore.ChangeSetPersister;

public class PlayerNotFoundException extends RuntimeException  {
    public PlayerNotFoundException(String e) {
        System.out.println(e);
    }
}
