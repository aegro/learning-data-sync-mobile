package br.com.aegro.datasync.edge.rest;

import br.com.aegro.datasync.edge.rest.resources.SeedResource;
import br.com.aegro.datasync.edge.rest.resources.converter.SeedResourceConverter;
import br.com.aegro.datasync.seed.application.SeedApplicationService;
import br.com.aegro.datasync.seed.application.SeedQueryApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/seed")
public class SeedRestController {

    private final SeedResourceConverter seedResourceConverter;

    private final SeedApplicationService seedApplicationService;

    private final SeedQueryApplicationService seedQueryApplicationService;

    public SeedRestController(
            SeedResourceConverter seedResourceConverter,
            SeedApplicationService seedApplicationService,
            SeedQueryApplicationService seedQueryApplicationService
    ) {
        this.seedResourceConverter = seedResourceConverter;
        this.seedApplicationService = seedApplicationService;
        this.seedQueryApplicationService = seedQueryApplicationService;
    }

    @GetMapping("/{userId}")
    public List<SeedResource> getAllByUser(@PathVariable String userId) {
        return seedQueryApplicationService.findAllByUser(userId)
                .stream().map(seedResourceConverter::convertTo)
                .collect(Collectors.toList());
    }
}
