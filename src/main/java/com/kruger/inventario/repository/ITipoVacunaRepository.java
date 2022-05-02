package com.kruger.inventario.repository;

import com.kruger.inventario.model.TipoVacuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoVacunaRepository extends JpaRepository<TipoVacuna, Integer> {
    TipoVacuna findByNombre(String nombre);
}
