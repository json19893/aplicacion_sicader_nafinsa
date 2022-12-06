package com.org.backend_nafinsa.service.impl;

import com.org.backend_nafinsa.dto.GananciaPerdidaUDIDto;
import com.org.backend_nafinsa.dto.GananciaRequest;
import com.org.backend_nafinsa.dto.ResponseDto;
import com.org.backend_nafinsa.entidad.SicaderPerdidaGananciaInfla;
import com.org.backend_nafinsa.entidad.SicaderPerdidaGananciaInflaDetalle;
import com.org.backend_nafinsa.repository.SicaderPerdidaGananciaInflaDetalleRepo;
import com.org.backend_nafinsa.repository.SicaderPerdidaGananciaInflaRepository;
import com.org.backend_nafinsa.service.GananciaService;
import com.org.backend_nafinsa.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GananciaServiceImpl implements GananciaService {
    @Autowired
    SicaderPerdidaGananciaInflaDetalleRepo sicaderPerdidaGananciaInflaDetalleRepo;
    @Autowired
    SicaderPerdidaGananciaInflaRepository sicaderPerdidaGananciaInflaRepository;

    @Override
    public ResponseDto cargaGanancia(GananciaRequest gananciaRequest) {
        Optional<SicaderPerdidaGananciaInfla> sicaderPerdidaGananciaInfla = sicaderPerdidaGananciaInflaRepository.findByFechaOperacion(gananciaRequest.getFechaOperacion());
        SicaderPerdidaGananciaInfla sicaderPerdidaGananciaInflaSave = new SicaderPerdidaGananciaInfla();
        SicaderPerdidaGananciaInflaDetalle sicaderPerdidaGananciaInflaDetalle = new SicaderPerdidaGananciaInflaDetalle();
        if (sicaderPerdidaGananciaInfla.isPresent() && !gananciaRequest.isForzar()) {
            return new ResponseDto(Constants.MENSAJE_EXISTE);

        }
        if (sicaderPerdidaGananciaInfla.isPresent()) {
            sicaderPerdidaGananciaInflaSave = sicaderPerdidaGananciaInfla.get();
            sicaderPerdidaGananciaInflaSave.setFechaModificacion(Constants.diaActual);
            sicaderPerdidaGananciaInflaSave.setUsuModificacion(gananciaRequest.getUsuario());
            sicaderPerdidaGananciaInflaSave.setEstatus("E");
            sicaderPerdidaGananciaInflaSave.getSicaderPerdidaGananciaInflaDetalle().clear();
        } else {
            sicaderPerdidaGananciaInflaSave.setFechaOperacion(gananciaRequest.getFechaOperacion());
            sicaderPerdidaGananciaInflaSave.setUsuRegistro(gananciaRequest.getUsuario());
            sicaderPerdidaGananciaInflaSave.setFechaRegistro(Constants.diaActual);
            sicaderPerdidaGananciaInflaSave.setEstatus("E");
        }
        sicaderPerdidaGananciaInflaDetalle.setPerdidaInflacionaria(gananciaRequest.getPerdida());
        sicaderPerdidaGananciaInflaDetalle.setValorUdi(gananciaRequest.getValorUid());
        sicaderPerdidaGananciaInflaDetalle.setFechaVencimiento(gananciaRequest.getFechaVencimiento());
        sicaderPerdidaGananciaInflaDetalle.setSicaderPerdidaGananciaInfla(sicaderPerdidaGananciaInflaSave);
        guardarPerdidaGanancia(sicaderPerdidaGananciaInflaSave, sicaderPerdidaGananciaInflaDetalle);
        return new ResponseDto("OK");
    }

    @Override
    public List<GananciaPerdidaUDIDto> getGananciaPerdidaUDI(LocalDate fechaOperacion) {
        List<Object[]> objectList = sicaderPerdidaGananciaInflaRepository.getGananciaPerdidaUDI(fechaOperacion);
        List<GananciaPerdidaUDIDto> gananciaPerdidaUDIDto = new ArrayList<>();
        gananciaPerdidaUDIDto.addAll(
                objectList.stream()
                        .map(ob -> new GananciaPerdidaUDIDto(ob))
                        .collect(Collectors.toList()));
        return gananciaPerdidaUDIDto;
    }

    @Transactional
    public void guardarPerdidaGanancia(SicaderPerdidaGananciaInfla sicaderPerdidaGananciaInflaSave, SicaderPerdidaGananciaInflaDetalle sicaderPerdidaGananciaInflaDetalle) {
        sicaderPerdidaGananciaInflaSave = sicaderPerdidaGananciaInflaRepository.saveAndFlush(sicaderPerdidaGananciaInflaSave);
        //sicaderPerdidaGananciaInflaDetalle.setSicaderPerdidaGananciaInfla(sicaderPerdidaGananciaInflaSave);
        sicaderPerdidaGananciaInflaDetalleRepo.save(sicaderPerdidaGananciaInflaDetalle);

    }

}
