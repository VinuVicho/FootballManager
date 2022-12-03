package me.vinuvicho.footballmanager.exeption;

public class PlayerNotFoundException extends RuntimeException  {
    public PlayerNotFoundException(String e) {
        System.out.println(e);
    }
}
