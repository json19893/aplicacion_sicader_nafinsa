package com.org.backend_nafinsa.service.impl;

import com.org.backend_nafinsa.dto.ConciliaPkg;
import com.org.backend_nafinsa.dto.SalidaPkg;
import com.org.backend_nafinsa.dto.ValidacionPkg;
import com.org.backend_nafinsa.exception.ErrorAplicacionControlado;
import com.org.backend_nafinsa.repository.CallPkgSicader;
import com.org.backend_nafinsa.repository.SicaderValidacionRepository;
import com.org.backend_nafinsa.service.ConciliaSicaderService;
import com.org.backend_nafinsa.util.CodigosRespuestaControlados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ConciliaSicaderServiceImpl implements ConciliaSicaderService {
    @Autowired
    CallPkgSicader callPkgSicader;
    @Autowired
    SicaderValidacionRepository sicaderValidacionRepository;

    @Override
    public SalidaPkg ejecutarConciliacion(ConciliaPkg conciliaPkg) {
        if (conciliaPkg.getInDerivado() == 4)
            conciliaPkg.setInDerivado(null);
        return callPkgSicader.SicaderConciliaPkg(conciliaPkg);
    }

    @Autowired
    CodigosRespuestaControlados respuestaControlada;

    @Override
    public boolean ejecutarValidacion(ValidacionPkg validacionPkg) {
        List<Long> tipoDerivado = new ArrayList<Long>();
        List<Long> validacion = new ArrayList<Long>(Arrays.asList(0L, null));
        callPkgSicader.SicaderValidaPkg(validacionPkg);
        if (!sicaderValidacionRepository.existsByFechaOperacion(validacionPkg.getFechaOperacion())) {
            throw new ErrorAplicacionControlado(
                    respuestaControlada.getFechaValidacion().get("codigo"),
                    this.getClass().getName(),
                    respuestaControlada.getFechaValidacion().get("mensaje")
            );
        }
        if (validacionPkg.getTipoValidacion() == 4) {
            tipoDerivado = new ArrayList<Long>(Arrays.asList(1L, 2L, 3L));
        } else {
            tipoDerivado = new ArrayList<Long>(Arrays.asList(validacionPkg.getDerivado()));
        }
        return !sicaderValidacionRepository.existsByFechaOperacionAndAndTipoDerivadoInAndAndValidacionIn
                (validacionPkg.getFechaOperacion(), tipoDerivado, validacion);
    }
}
