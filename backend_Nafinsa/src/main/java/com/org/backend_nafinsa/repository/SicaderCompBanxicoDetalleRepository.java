package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderCompBanxico;
import com.org.backend_nafinsa.entidad.SicaderCompBanxicoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SicaderCompBanxicoDetalleRepository extends JpaRepository<SicaderCompBanxicoDetalle, Long> {
}
