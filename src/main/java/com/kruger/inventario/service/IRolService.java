package com.kruger.inventario.service;

import com.kruger.inventario.model.Dto.DtoInfo;
import com.kruger.inventario.model.Rol;

public interface IRolService {
    public DtoInfo crearRol(Rol rol);
    DtoInfo actualizarRol(Rol rol);
}
