package com.kruger.inventario.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @NotEmpty(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombres;

    @NotEmpty(message = "El apellido es obligatorio")
    @Column(nullable = false)
    private String apellidos;

    @NotEmpty(message = "El telefono es obligatorio")
    @Column(nullable = false)
    @Size(min = 7, max = 9, message = "El telefono debe tener al menos 7 digitos")
    private String telefono;

    @NotEmpty(message = "El numero celular no puede estar vacio")
    @Column(nullable = false)
    @Size(min = 10, max = 13, message = "El numero celular debe tener al menos 10 digitos")
    private String celular;

    @NotEmpty(message = "La direccion no puede estar vacia")
    @Column(nullable = false)
    @Size(min = 50 , max = 200, message = "La direccion debe tener entre 50 y 200 caracteres")
    private String direccion;

    @NotEmpty(message = "La cedula es obligatoria")
    @Column(nullable = false, length = 10, unique = true)
    @Size(min = 10, max = 10, message = "La cedula debe tener 10 digitos")
    private String cedula;

    @NotEmpty(message = "El correo es obligatorio")
    @Email(message = "El correo no es valido")
    @Column(nullable = false)
    private String email;

    @NotEmpty(message = "Este campo es obligatorio")
    @Column(nullable = false)
    @Size(min = 8, max = 12, message = "La contraseña debe tener entre 8 y 12 caracteres")
    private String password;

    private boolean estado = true;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;
}
