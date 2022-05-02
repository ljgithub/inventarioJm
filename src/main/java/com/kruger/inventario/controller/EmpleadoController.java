package com.kruger.inventario.controller;

import com.kruger.inventario.model.Dto.DtoInfo;
import com.kruger.inventario.model.Usuario;
import com.kruger.inventario.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<DtoInfo> createEmpleado(@RequestBody Usuario usuario){
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
}
