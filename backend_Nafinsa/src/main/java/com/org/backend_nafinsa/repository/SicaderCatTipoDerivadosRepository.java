package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderCatTipoDerivados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SicaderCatTipoDerivadosRepository extends JpaRepository<SicaderCatTipoDerivados, Long> {
}
