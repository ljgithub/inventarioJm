package com.kruger.inventario.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DtoInfo {
    private boolean Exito;
    private String Mensaje;

    public DtoInfo(){
        this.Exito=false;
        this.Mensaje="";
    }

}
