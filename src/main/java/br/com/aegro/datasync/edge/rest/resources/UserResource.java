package br.com.aegro.datasync.edge.rest.resources;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public record UserResource(@NotNull String id, @NotNull String fullName, @Email String email) {

}
