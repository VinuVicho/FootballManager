package me.vinuvicho.footballmanager.exeption;

public class PlayerNotFoundException extends /*RuntimeException*/ IllegalStateException {
    public PlayerNotFoundException(String e) {
        super(e);
    }
}
