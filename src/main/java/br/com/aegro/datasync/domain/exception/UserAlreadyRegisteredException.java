package br.com.aegro.datasync.domain.exception;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException(String email) {
        super("There's an user registered for the email " + email);
    }
}
