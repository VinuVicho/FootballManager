package me.vinuvicho.footballmanager.exeption;

import javax.xml.bind.ValidationException;

public class PlayerValidationFailed extends ValidationException {
    public PlayerValidationFailed(String e) {
        super(e);
    }
}
