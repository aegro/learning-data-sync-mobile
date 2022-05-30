package br.com.aegro.datasync.edge.rest.resources.converter;

import br.com.aegro.datasync.edge.rest.resources.SeedResource;
import br.com.aegro.datasync.seed.application.model.SeedModel;
import br.com.aegro.datasync.user.application.model.UserModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StandardSeedResourceConverter implements SeedResourceConverter {
    @Override
    public SeedResource convertTo(SeedModel seedModel) {
        return new SeedResource(
                seedModel.getId(),
                seedModel.getName(),
                seedModel.getManufacturer(),
                seedModel.getManufacturedAt().format(DateTimeFormatter.ISO_DATE),
                seedModel.getExpiresIn().format(DateTimeFormatter.ISO_DATE),
                seedModel.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME),
                seedModel.getCreatedBy().getId()
        );
    }

    @Override
    public SeedModel convertFrom(SeedResource seedResource) {
        var userModel = new UserModel(seedResource.userId(), "", "");
        return new SeedModel(
                seedResource.id(),
                seedResource.name(),
                seedResource.manufacturer(),
                LocalDate.parse(seedResource.manufacturedAt()),
                LocalDate.parse(seedResource.expiresIn()),
                LocalDateTime.parse(seedResource.createdAt()),
                userModel
        );
    }
}
