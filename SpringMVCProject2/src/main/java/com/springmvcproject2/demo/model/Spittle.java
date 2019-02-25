package com.springmvcproject2.demo.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

public class Spittle {
    private final Long id;
    private final String message;
    private final Date time;
    private Double latitude;
    private Double longitude;

    public Spittle(String message, Date time) {
        this(message, time, null, null);
    }

    public Spittle(String message,Date time, Double latitude, Double longitude) {
        this.id = null;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getTime() {
        return time;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj,
                new String[]{"message","latitude", "longitude"});
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this,
                new String[]{"message", "latitude", "longitude"});
    }
}
