package br.com.aegro.datasync.seed.persistence.model;

import br.com.aegro.datasync.user.persistence.model.UserData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_seed")
public class SeedData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String externalId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate manufacturedAt;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate expiresIn;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserData createdBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
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

    public UserData getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserData createdBy) {
        this.createdBy = createdBy;
    }
}
