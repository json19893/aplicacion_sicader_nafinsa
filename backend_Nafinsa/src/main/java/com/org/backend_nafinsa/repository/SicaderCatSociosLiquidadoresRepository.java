package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderCatSociosLiquidadores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SicaderCatSociosLiquidadoresRepository extends JpaRepository<SicaderCatSociosLiquidadores, Long> {

    SicaderCatSociosLiquidadores findByNombreLike(String nombre);

    List<SicaderCatSociosLiquidadores> findAllByOrderByNombreAsc();

}
