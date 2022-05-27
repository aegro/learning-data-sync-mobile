package br.com.aegro.datasync.edge.rest.resources;

import javax.validation.constraints.Email;

public record AuthResource(@Email String email) {
}
