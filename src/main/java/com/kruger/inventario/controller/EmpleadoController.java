package com.kruger.inventario.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kruger.inventario.model.Dto.DtoInfo;
import com.kruger.inventario.model.Usuario;
import com.kruger.inventario.service.UsuarioServiceImpl;
import com.kruger.inventario.util.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAll(){
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<DtoInfo> createEmpleado(@RequestBody Usuario usuario, BindingResult result){
        log.info("Creando empleado");
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }

        return ResponseEntity.ok(usuarioService.crearUsuarioEmpleado(usuario));
    }

    @PutMapping
    public ResponseEntity<DtoInfo> updateEmpleado(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.updateEmpleado(usuario));
    }

    @DeleteMapping
    public ResponseEntity<DtoInfo> deleteEmpleado(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.deleteEmpleado(usuario));
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
