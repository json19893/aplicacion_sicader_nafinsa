package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderCuentasConciliar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SicaderCuentasConciliarRepository extends JpaRepository<SicaderCuentasConciliar, Long> {
}
