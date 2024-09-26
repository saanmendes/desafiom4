package com.schedule.controllers;

import com.schedule.controllers.dtos.ElementsDTO;
import com.schedule.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    @PostMapping("/event")
    public ResponseEntity<?> create(@RequestBody ElementsDTO elementsDTO) {
        List<String> errors = validateEvent(elementsDTO);
        if(!errors.isEmpty()) {
            return ResponseEntity.unprocessableEntity().body(errors);
        }
        ElementsDTO createdEvent = scheduleService.createEvent(elementsDTO);
        return ResponseEntity.status(201).body(createdEvent);
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id) {
        boolean deleted = scheduleService.deleteEvent(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/event/{id}/cancel")
    public ResponseEntity<Void> cancelEvent(@PathVariable String id) {
        boolean canceled = scheduleService.cancelEvent(id);
        if (canceled) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    private List<String> validateEvent(ElementsDTO elementsDTO) {
        List<String> errors = new ArrayList<>();

        if ( elementsDTO.getNameOfTheEvent().isEmpty()) {
            errors.add("O nome do evento é obrigatório.");
        }
        if (elementsDTO.getDescription().isEmpty()) {
            errors.add("A descrição é obrigatória.");
        }
        if (elementsDTO.getEntryTime() == null) {
            errors.add("A data de início é obrigatória.");
        }
        if (elementsDTO.getExitTime() == null) {
            errors.add("A data de término é obrigatória.");
        }
        if (elementsDTO.getStartTime() == null || elementsDTO.getEndTime() == null) {
            errors.add("Os horários de início e término são obrigatórios.");
        }
        return errors;
    }

}

