package me.vinuvicho.footballmanager.exeption;

public class PlayerNotFoundException extends RuntimeException  {
    public PlayerNotFoundException(String e) {
        super(e);
    }
}
