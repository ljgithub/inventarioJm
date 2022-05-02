package com.kruger.inventario.service;

import com.kruger.inventario.model.Dto.DtoInfo;
import com.kruger.inventario.model.TipoVacuna;
import com.kruger.inventario.repository.ITipoVacunaRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoVacunaServiceImpl implements ITIpoVacunaService {
    private DtoInfo dtoInfo = new DtoInfo();

    @Autowired
    ITipoVacunaRepository tipoVacunaRepository;

    @Override
    public TipoVacuna findByNombre(String name) {
        TipoVacuna tipoVacuna= new TipoVacuna();

        tipoVacuna = tipoVacunaRepository.findByNombre(name);
        if(tipoVacuna == null){
            tipoVacuna = TipoVacuna.builder().idTipoVacuna(0).build();
        }

        return tipoVacuna;
    }

    @Override
    public DtoInfo createTipoVacuna(TipoVacuna tipoVacuna) {
        if (findByNombre(tipoVacuna.getNombre()).getIdTipoVacuna() >0) {
            return new DtoInfo(false, "Ya existe un tipo de vacuna con ese nombre");
        }else {
            tipoVacunaRepository.save(tipoVacuna);
            dtoInfo.setExito(true);
            dtoInfo.setMensaje("Tipo de vacuna creado con exito");
        }
        return dtoInfo;
    }

    @Override
    public DtoInfo updateTipoVacuna(TipoVacuna tipoVacuna) {
        var currentTipoVacuna = tipoVacunaRepository.findByNombre(tipoVacuna.getNombre());
        if (currentTipoVacuna.getIdTipoVacuna() >0) {
            return new DtoInfo(false, "Ya existe un tipo de vacuna con ese nombre");
        }else {
            currentTipoVacuna.setNombre(tipoVacuna.getNombre());
            currentTipoVacuna.setEstado(tipoVacuna.isEstado());

            tipoVacunaRepository.save(currentTipoVacuna);
            dtoInfo.setExito(true);
            dtoInfo.setMensaje("Tipo de vacuna actualizado con exito");
        }
        return dtoInfo;
    }
}
