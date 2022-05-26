package br.com.aegro.datasync.edge.rest;

import br.com.aegro.datasync.edge.rest.resources.AuthResource;
import br.com.aegro.datasync.edge.rest.resources.UserResource;
import br.com.aegro.datasync.edge.rest.resources.converter.UserResourceConverter;
import br.com.aegro.datasync.user.application.UserApplicationService;
import br.com.aegro.datasync.user.application.model.UserAlreadyRegisteredException;
import br.com.aegro.datasync.user.application.model.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/user")
public class UserRestController {

    private final UserApplicationService userApplicationService;

    private final UserResourceConverter userResourceConverter;

    public UserRestController(
            UserApplicationService userApplicationService,
            UserResourceConverter userResourceConverter
    ) {
        this.userApplicationService = userApplicationService;
        this.userResourceConverter = userResourceConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody UserResource userResource) {
        try {
            userApplicationService.save(userResourceConverter.convertFrom(userResource));
        } catch (UserAlreadyRegisteredException userAlreadyRegisteredException) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, null, userAlreadyRegisteredException);
        }
    }

    @PostMapping(value = "/auth")
    public UserResource authenticate(@Valid @RequestBody AuthResource authResource) {
        try {
            var userModel = userApplicationService.authenticate(authResource.email());
            return userResourceConverter.convertTo(userModel);
        } catch (UserNotFoundException userNotFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, userNotFoundException);
        }
    }

}
