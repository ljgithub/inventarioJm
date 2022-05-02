package com.kruger.inventario.service;

import com.kruger.inventario.model.Dto.DtoInfo;
import com.kruger.inventario.model.Vacuna;

public interface IVacunaService {
    Vacuna findByNombre(String nombre);
    DtoInfo createVacuna(Vacuna vacuna);
    DtoInfo updateVacuna(Vacuna vacuna);
}
