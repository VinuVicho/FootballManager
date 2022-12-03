package me.vinuvicho.footballmanager.exeption;

import javax.xml.bind.ValidationException;

public class TeamValidationFailed extends ValidationException {
    public TeamValidationFailed(String e) {
        super(e);
    }
}
