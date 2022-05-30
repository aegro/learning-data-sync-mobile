package br.com.aegro.datasync.user.domain.model;

import java.util.Objects;

public class User {

    private Long id;
    private String externalId;
    private String fullName;
    private String email;

    public User(Long id, String externalId, String fullName, String email) {
        if (externalId == null) throw new NullPointerException("id cannot be null");
        if (fullName == null) throw new NullPointerException("fullName cannot be null");
        if (email == null) throw new NullPointerException("email cannot be null");

        this.id = id;
        this.externalId = externalId;
        this.fullName = fullName;
        this.email = email;
    }

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                externalId.equals(user.externalId) &&
                fullName.equals(user.fullName) &&
                email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, externalId, fullName, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", externalId='" + externalId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
