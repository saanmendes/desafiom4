package com.schedule.services;

import com.schedule.controllers.dtos.ElementsDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<ElementsDTO> getSCHEDULE(){
        return SCHEDULE;
    }
}



