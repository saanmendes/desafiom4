package com.schedule.services;

import com.schedule.controllers.dtos.ElementsDTO;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Service
public class ScheduleService {

    private final List<ElementsDTO> SCHEDULE =new ArrayList<>();
    private final Random random = new Random();

    public ElementsDTO createEvent(ElementsDTO elementsDTO) {
        elementsDTO.setId(String.valueOf(random.nextInt(10000)));
        SCHEDULE.add(elementsDTO);
        return elementsDTO;
    }

    public boolean deleteEvent(String id) {
        return SCHEDULE.removeIf(event -> event.getId().equals(id));
    }

    public boolean cancelEvent(String id) {
        for (ElementsDTO event : SCHEDULE) {
            if (event.getId().equals(id)) {
                event.setEventActive(false);
                return true;
            }
        }
        return false;
    }

     private LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public List<ElementsDTO> getFutureEvents() {
        LocalDate today = LocalDate.now();
        List<ElementsDTO> futureEvents = new ArrayList<>();
        for (ElementsDTO event : SCHEDULE) {
            if (event.getEntryTime() != null) {
                LocalDate eventDate = convertToLocalDate(event.getEntryTime());
                if (eventDate.isAfter(today)) {
                    futureEvents.add(event);
                }
            }
        }
        return futureEvents;
    }

    public List<ElementsDTO> getEventsWithinPeriod(int dias) {
        LocalDate today = LocalDate.now();
        List<ElementsDTO> events = new ArrayList<>();

        if (dias < 0) {
            for (ElementsDTO event : SCHEDULE) {
                if (event.getEntryTime() != null) {
                    LocalDate eventDate = convertToLocalDate(event.getEntryTime());
                    if (eventDate.isBefore(today)) {
                        events.add(event);
                    }
                }
            }
        } else {
            LocalDate targetDate = today.plusDays(dias);
            for (ElementsDTO event : SCHEDULE ) {
                if (event.getEntryTime() != null) {
                    LocalDate eventDate = convertToLocalDate(event.getEntryTime());
                    if (eventDate.isAfter(today) && eventDate.isBefore(targetDate)) {
                        events.add(event);
                    }
                }
            }
        }

        return events;
    }

    public List<ElementsDTO> getEventsForTime(LocalTime time) {
        List<ElementsDTO> eventsForTime = new ArrayList<>();

        for (ElementsDTO event : SCHEDULE) {
            if (event.getStartTime() != null) {

                LocalTime eventStartTime = event.getStartTime().toLocalTime();

                if (eventStartTime.equals(time)) {
                    eventsForTime.add(event);
                }
            }
        }

        return eventsForTime;
    }

    public List<ElementsDTO> getSCHEDULE(){
        return SCHEDULE;
    }
}



