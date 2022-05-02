package com.kruger.inventario.controller;

import com.kruger.inventario.model.Dto.DtoInfo;
import com.kruger.inventario.model.Rol;
import com.kruger.inventario.service.RolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    RolServiceImpl rolService;

    @PostMapping("/save")
    public ResponseEntity<DtoInfo> createRol(@RequestBody Rol rol){
        try {
            rolService.crearRol(rol);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    //Create method to update a rol
    @PutMapping("/update")
    public ResponseEntity<DtoInfo> updateRol(@RequestBody Rol rol){
        try {
            rolService.actualizarRol(rol);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
