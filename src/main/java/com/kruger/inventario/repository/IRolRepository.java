package com.kruger.inventario.repository;

import com.kruger.inventario.model.Rol;
import com.kruger.inventario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {

}
