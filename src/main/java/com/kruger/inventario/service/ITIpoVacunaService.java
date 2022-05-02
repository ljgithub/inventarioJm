package com.kruger.inventario.service;

import com.kruger.inventario.model.Dto.DtoInfo;
import com.kruger.inventario.model.TipoVacuna;

public interface ITIpoVacunaService {
    TipoVacuna findByNombre(String name);
    DtoInfo createTipoVacuna(TipoVacuna tipoVacuna);
    DtoInfo updateTipoVacuna(TipoVacuna tipoVacuna);
}
