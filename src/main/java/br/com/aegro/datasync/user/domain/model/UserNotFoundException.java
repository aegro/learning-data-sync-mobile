package br.com.aegro.datasync.user.domain.model;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String id) {
        super("Unable to find an user for id " + id);
    }

}
