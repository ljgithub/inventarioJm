package com.kruger.inventario.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kruger.inventario.model.Dto.DtoInfo;
import com.kruger.inventario.model.Vacuna;
import com.kruger.inventario.service.VacunaServiceImpl;
import com.kruger.inventario.util.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/vacuna")

public class VacunaController {
    @Autowired
    private VacunaServiceImpl vacunaService;

    @PostMapping("/save")
    public ResponseEntity<DtoInfo> crearVacuna(@Valid @RequestBody Vacuna vacuna, BindingResult result) {
        log.info("Creando vacuna");
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        return ResponseEntity.ok(vacunaService.createVacuna(vacuna));
    }

    @PutMapping("/update")
    public ResponseEntity<DtoInfo> actualizarVacuna(@Valid @RequestBody Vacuna vacuna, BindingResult result) {
        log.info("Actualizando vacuna");
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        return ResponseEntity.ok(vacunaService.updateVacuna(vacuna));
    }


    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
