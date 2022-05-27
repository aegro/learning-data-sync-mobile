package br.com.aegro.datasync.edge.rest.resources;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UserResource(@NotNull @NotBlank String id, @NotNull @NotBlank String fullName, @Email String email) {

}
