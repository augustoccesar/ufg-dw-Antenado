package model;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

/**
 * Created by augustoccesar on 7/4/16.
 */
public class User {
    private Long id;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    private String username;

    private String email;

    private String password;

    private String gender;

    private transient Timestamp createdAt;

    private transient Timestamp deletedAt;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getCreatedAt() {
        if (createdAt != null) {
            return createdAt.getTime();
        } else {
            return null;
        }
    }

    public void setCreatedAt(Long createdAt) {
        if (createdAt != null) {
            this.createdAt = new Timestamp(createdAt);
        } else {
            this.createdAt = null;
        }
    }

    public Long getDeletedAt() {
        if (deletedAt != null) {
            return deletedAt.getTime();
        } else {
            return null;
        }
    }

    public void setDeletedAt(Long deletedAt) {
        if (deletedAt != null) {
            this.deletedAt = new Timestamp(deletedAt);
        } else {
            this.deletedAt = null;
        }
    }
}
