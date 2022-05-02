package com.kruger.inventario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "rol")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;

    @NotEmpty(message = "El nombre del rol es obligatorio")
    @Column(nullable = false)
    private String nombreRol;

    @NotEmpty(message = "La descripcion del rol es obligatoria")
    @Column(nullable = false)
    private String descripcionRol;

    private int estado = 1;
}
