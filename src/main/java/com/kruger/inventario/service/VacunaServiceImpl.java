package com.kruger.inventario.service;

import com.kruger.inventario.model.Dto.DtoInfo;
import com.kruger.inventario.model.Vacuna;
import com.kruger.inventario.repository.IVacunaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VacunaServiceImpl implements IVacunaService{
    DtoInfo dtoInfo = new DtoInfo();

    @Autowired
    public IVacunaRepository vacunaRepository;

    @Override
    public Vacuna findByNombre(String nombre) {
        Vacuna  currentVacuna = new Vacuna();

        currentVacuna = vacunaRepository.findByNombre(nombre);
        if(currentVacuna == null){
            currentVacuna = Vacuna.builder().idVacuna(0).build();
        }

        return currentVacuna;
    }

    @Override
    public DtoInfo createVacuna(Vacuna vacuna) {
        if(findByNombre(vacuna.getNombre()).getIdVacuna() > 0){
            dtoInfo.setMensaje("La vacuna ya existe");
        }else {
            vacunaRepository.save(vacuna);
            dtoInfo.setExito(true);
            dtoInfo.setMensaje("Vacuna creada");
        }
        return dtoInfo;
    }

    @Override
    public DtoInfo updateVacuna(Vacuna vacuna) {
        Vacuna currentVacuna = findByNombre(vacuna.getNombre());
        if(currentVacuna.getIdVacuna() > 0){
            dtoInfo.setMensaje("La vacuna ya existe");
        }else {
            currentVacuna.setNombre(vacuna.getNombre());
            currentVacuna.setTipoVacuna(vacuna.getTipoVacuna());
            currentVacuna.setDescripcion(vacuna.getDescripcion());
            currentVacuna.setEstado(vacuna.isEstado());

            vacunaRepository.save(currentVacuna);
            dtoInfo.setExito(true);
            dtoInfo.setMensaje("Vacuna actualizada");
        }
        return dtoInfo;
    }
}
