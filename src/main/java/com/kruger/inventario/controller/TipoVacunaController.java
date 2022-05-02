package com.kruger.inventario.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kruger.inventario.model.Dto.DtoInfo;
import com.kruger.inventario.model.TipoVacuna;
import com.kruger.inventario.service.ITIpoVacunaService;
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
@RequestMapping("/tipoVacuna")
public class TipoVacunaController {

    @Autowired
    private ITIpoVacunaService tipoVacunaService;

    //Controller to create TipoVacuna
    @PostMapping("/save")
    public ResponseEntity<DtoInfo> createTipoVacuna(@Valid @RequestBody TipoVacuna tipoVacuna, BindingResult result){
      log.info("Creando TipoVacuna");
      if (result.hasErrors()){
          log.info("Error al crear TipoVacuna");
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
      }
      return ResponseEntity.ok(tipoVacunaService.createTipoVacuna(tipoVacuna));
    }

    @PutMapping("/update")
    public ResponseEntity<DtoInfo> updateTipoVacuna(@Valid @RequestBody TipoVacuna tipoVacuna, BindingResult result){
        log.info("Actualizando TipoVacuna");
        if (result.hasErrors()){
            log.info("Error al actualizar TipoVacuna");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        return ResponseEntity.ok(tipoVacunaService.updateTipoVacuna(tipoVacuna));
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
