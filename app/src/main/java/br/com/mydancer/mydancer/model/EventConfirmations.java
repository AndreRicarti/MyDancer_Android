package br.com.mydancer.mydancer.model;

import java.util.Date;

public class EventConfirmations {
    private int Id;
    private int EventId;
    private int UserId;
    private Date DateCreation;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getEventId() {
        return EventId;
    }

    public void setEventId(int eventId) {
        EventId = eventId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public Date getDateCreation() {
        return DateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        DateCreation = dateCreation;
    }
}
