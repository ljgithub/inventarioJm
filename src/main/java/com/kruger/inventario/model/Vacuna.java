package com.kruger.inventario.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "vacuna")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vacuna {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer idVacuna;

    @NotEmpty(message = "El nombre de la vacuna es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty(message = "La descripcion de la vacuna es obligatoria")
    @Column(nullable = false)
    private String descripcion;

    private boolean estado = true;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_tipo_vacuna")
    private TipoVacuna tipoVacuna;
}
