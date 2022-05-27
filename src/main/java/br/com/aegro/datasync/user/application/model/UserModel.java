package br.com.aegro.datasync.user.application.model;

import java.util.Objects;

public class UserModel {

    private String id;
    private String fullName;
    private String email;

    public UserModel(String id, String fullName, String email) {
        if (id == null) throw new NullPointerException("id cannot be null");
        if (fullName == null) throw new NullPointerException("fullName cannot be null");
        if (email == null) throw new NullPointerException("email cannot be null");

        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        UserModel userModel = (UserModel) o;
        return id.equals(userModel.id) && fullName.equals(userModel.fullName) && email.equals(userModel.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, email);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
