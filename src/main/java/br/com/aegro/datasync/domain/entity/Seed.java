package br.com.aegro.datasync.domain.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Seed {

    private String id;
    private String name;
    private String manufacturer;
    private LocalDate manufacturedAt;
    private LocalDate expiresIn;
    private LocalDate createdAt;
    private User createdBy;

    public Seed(
            String id,
            String name,
            String manufacturer,
            LocalDate manufacturedAt,
            LocalDate expiresIn,
            LocalDate createdAt,
            User createdBy
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seed seed = (Seed) o;
        return id.equals(seed.id) &&
                name.equals(seed.name) &&
                manufacturer.equals(seed.manufacturer) &&
                manufacturedAt.equals(seed.manufacturedAt) &&
                expiresIn.equals(seed.expiresIn) &&
                createdAt.equals(seed.createdAt) &&
                createdBy.equals(seed.createdBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                name,
                manufacturer,
                manufacturedAt,
                expiresIn,
                createdAt,
                createdBy
        );
    }

    @Override
    public String toString() {
        return "Seed{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", manufacturedAt=" + manufacturedAt +
                ", expiresIn=" + expiresIn +
                ", createdAt=" + createdAt +
                ", createdBy=" + createdBy +
                '}';
    }
}
