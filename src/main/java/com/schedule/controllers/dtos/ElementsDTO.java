package com.schedule.controllers.dtos;

import java.sql.Time;
import java.util.Date;

public class ElementsDTO {

    private String id;
    private String nameOfTheEvent;
    private String description;
    private Date entryTime;
    private Date exitTime;
    private Time startTime;
    private Time endTime;
    private boolean eventActive;

    public ElementsDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameOfTheEvent() {
        return nameOfTheEvent;
    }

    public void setNameOfTheEvent(String nameOfTheEvent) {
        this.nameOfTheEvent = nameOfTheEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public boolean isEventActive() {
        return eventActive;
    }

    public void setEventActive(boolean eventActive) {
        this.eventActive = eventActive;
    }
}
