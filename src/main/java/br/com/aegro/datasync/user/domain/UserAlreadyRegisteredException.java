package br.com.aegro.datasync.user.domain;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException(String email) {
        super("There's an user registered for the email " + email);
    }
}
