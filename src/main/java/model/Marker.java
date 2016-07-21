package model;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

/**
 * Created by augustoccesar on 7/4/16.
 */
public class Marker {
    private Long id;

    private String title;

    private String message;

    private Integer priority;

    private Double latitude;

    private Double longitude;

    private transient Timestamp createdAt;

    private transient Timestamp deletedAt;

    // Not persisted

    @SerializedName("time_ago")
    private String timeAgo;

    // Constructors

    public Marker() {
    }

    // Relations

    private User user;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getCreatedAt() {
        if (createdAt != null) {
            return createdAt.getTime();
        } else {
            return null;
        }
    }

    public Timestamp getCreatedAtAsTimestamp() {
        return createdAt;
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

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
