package com.kruger.inventario.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tipo_vacuna")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoVacuna {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "id_tipo_vacuna")
    private Integer idTipoVacuna;

    @NotEmpty(message = "El nombre de la vacuna es obligatorio")
    @Size(min = 10 ,max = 30, message = "El nombre de la vacuna debe tener entre 10 y 30 caracteres")
    private String nombre;

    private boolean estado = true;
}
