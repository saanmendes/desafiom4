package com.schedule.controllers.dtos;

public class EventDTO {
    private String nameOfTheEvent;
    private String description;

    public EventDTO() {
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
}
