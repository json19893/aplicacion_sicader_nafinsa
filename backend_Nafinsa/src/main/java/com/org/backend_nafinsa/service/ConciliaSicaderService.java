package com.org.backend_nafinsa.service;

import com.org.backend_nafinsa.dto.ConciliaPkg;
import com.org.backend_nafinsa.dto.SalidaPkg;
import com.org.backend_nafinsa.dto.ValidacionPkg;

import java.util.List;

public interface ConciliaSicaderService {
    SalidaPkg ejecutarConciliacion(ConciliaPkg conciliaPkg);

    boolean ejecutarValidacion(ValidacionPkg validacionPkg);
}
