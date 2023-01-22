package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderCatContraparte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SicaderCatContraparteRepository extends JpaRepository<SicaderCatContraparte, Long> {

    List<SicaderCatContraparte> findAllByOrderByNombreAsc();
}
