package com.kruger.inventario.service;

import com.kruger.inventario.model.Dto.DtoInfo;
import com.kruger.inventario.model.Rol;
import com.kruger.inventario.repository.IRolRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements IRolService{
    private DtoInfo dtoInfoResponse;

    @Autowired
    IRolRepository rolRepository;

    @Override
    public DtoInfo crearRol(Rol rol) {
        var newRol = rolRepository.save(rol);
        if (newRol!=null) {
            dtoInfoResponse = new DtoInfo(true, "Rol creado con exito");
        }
        return dtoInfoResponse;
    }

    @Override
    public DtoInfo actualizarRol(Rol rol) {
        Rol currentRol = rolRepository.findById(rol.getIdRol()).orElse(null);
        if (currentRol == null){
            return null;
        }else {
            currentRol.setDescripcionRol(rol.getDescripcionRol());
            currentRol.setEstado(rol.getEstado());
            rolRepository.save(currentRol);
            dtoInfoResponse = new DtoInfo(true, "Rol actualizado con exito");
            return dtoInfoResponse;
        }
    }
}
