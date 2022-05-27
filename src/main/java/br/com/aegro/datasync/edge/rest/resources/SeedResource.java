package br.com.aegro.datasync.edge.rest.resources;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record SeedResource(
        @NotNull
        @NotBlank
        String id,
        @NotNull
        @NotBlank
        String name,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
        String manufacturer,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
        String manufacturedAt,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
        String expiresIn,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        String createdAt,
        @NotNull
        @NotBlank
        String userId
) {
}
