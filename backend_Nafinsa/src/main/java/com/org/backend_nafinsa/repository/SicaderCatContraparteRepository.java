package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderCatContraparte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SicaderCatContraparteRepository extends JpaRepository<SicaderCatContraparte, Long> {
}
