package br.com.aegro.datasync.seed.application.model;

import br.com.aegro.datasync.user.application.model.UserModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class SeedModel {

    private String id;
    private String name;
    private String manufacturer;
    private LocalDate manufacturedAt;
    private LocalDate expiresIn;
    private LocalDateTime createdAt;
    private UserModel createdBy;

    public SeedModel(
            String id,
            String name,
            String manufacturer,
            LocalDate manufacturedAt,
            LocalDate expiresIn,
            LocalDateTime createdAt,
            UserModel createdBy
    ) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.manufacturedAt = manufacturedAt;
        this.expiresIn = expiresIn;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public LocalDate getManufacturedAt() {
        return manufacturedAt;
    }

    public void setManufacturedAt(LocalDate manufacturedAt) {
        this.manufacturedAt = manufacturedAt;
    }

    public LocalDate getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(LocalDate expiresIn) {
        this.expiresIn = expiresIn;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserModel getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserModel createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeedModel seedModel = (SeedModel) o;
        return id.equals(seedModel.id) &&
                name.equals(seedModel.name) &&
                manufacturer.equals(seedModel.manufacturer) &&
                manufacturedAt.equals(seedModel.manufacturedAt) &&
                expiresIn.equals(seedModel.expiresIn) &&
                createdAt.equals(seedModel.createdAt) &&
                createdBy.equals(seedModel.createdBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, manufacturer, manufacturedAt, expiresIn, createdAt, createdBy);
    }

    @Override
    public String toString() {
        return "SeedModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", manufacturedAt=" + manufacturedAt +
                ", expiresIn=" + expiresIn +
                ", createdAt=" + createdAt +
                ", createdBy=" + createdBy +
                '}';
    }
}
