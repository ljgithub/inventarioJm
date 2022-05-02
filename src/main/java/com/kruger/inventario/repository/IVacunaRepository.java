package com.kruger.inventario.repository;

import com.kruger.inventario.model.Vacuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVacunaRepository extends JpaRepository<Vacuna, Integer> {
    Vacuna findByNombre(String nombre);
}
