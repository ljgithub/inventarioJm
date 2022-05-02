package com.kruger.inventario.service;

import com.kruger.inventario.model.Dto.DtoInfo;
import com.kruger.inventario.model.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> getAll();
    Usuario findUsuarioByCedula(String cedula);
    DtoInfo crearUsuarioEmpleado(Usuario usuario);
    DtoInfo updateEmpleado(Usuario usuario);
    DtoInfo deleteEmpleado(Usuario usuario);
}
