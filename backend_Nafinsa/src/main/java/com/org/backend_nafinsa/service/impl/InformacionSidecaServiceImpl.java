package com.org.backend_nafinsa.service.impl;

import com.org.backend_nafinsa.dto.*;
import com.org.backend_nafinsa.entidad.SicaderEdoCtaAsigna;
import com.org.backend_nafinsa.entidad.SicaderEdoCtaAsignaDetalle;
import com.org.backend_nafinsa.entidad.SicaderEdoCtaFuturos;
import com.org.backend_nafinsa.entidad.SicaderEdoCtaFuturosDetalle;
import com.org.backend_nafinsa.repository.SicaderEdoCtaAsignaDetalleRepository;
import com.org.backend_nafinsa.repository.SicaderEdoCtaAsignaRepository;
import com.org.backend_nafinsa.repository.SicaderEdoCtaFuturosDetalleRepository;
import com.org.backend_nafinsa.repository.SicaderEdoCtaFuturosRepository;
import com.org.backend_nafinsa.service.InformacionSidecaService;
import com.org.backend_nafinsa.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InformacionSidecaServiceImpl implements InformacionSidecaService {
    @Autowired
    SicaderEdoCtaFuturosRepository sicaderEdoCtaFuturosRepository;
    @Autowired
    SicaderEdoCtaFuturosDetalleRepository sicaderEdoCtaFuturosDetalleRepository;

    @Autowired
    SicaderEdoCtaAsignaRepository sicaderEdoCtaAsignaRepository;

    @Autowired
    SicaderEdoCtaAsignaDetalleRepository sicaderEdoCtaAsignaDetalleRepository;

    @Override
    public ResponseDto cargaCartaConfirmacion(CartaConfirmacionRequest cartaConfirmacionRequest) {
        Optional<SicaderEdoCtaFuturos> sicaderEdoCtaFuturos = sicaderEdoCtaFuturosRepository.findByFechaOperacion(cartaConfirmacionRequest.getFechaOperacion());
        SicaderEdoCtaFuturos sicaderEdoCtaFuturosSave = new SicaderEdoCtaFuturos();
        SicaderEdoCtaFuturosDetalle sicaderEdoCtaFuturosDetalle = new SicaderEdoCtaFuturosDetalle();
        if (sicaderEdoCtaFuturos.isPresent()) {
            sicaderEdoCtaFuturosSave = sicaderEdoCtaFuturos.get();
            sicaderEdoCtaFuturosSave.setUsuModificacion(cartaConfirmacionRequest.getUsuario());
            sicaderEdoCtaFuturosSave.setFechaModificacion(Constants.diaActual);
            long existe = sicaderEdoCtaFuturosSave.getSicaderEdoCtaFuturosDetalles()
                    .stream()
                    .filter(lista -> lista.getSocioId() == cartaConfirmacionRequest.getIdSocioLiquidador())
                    .count();
            if (existe >= 1 && !cartaConfirmacionRequest.isForzar()) {
                return new ResponseDto(Constants.MENSAJE_EXISTE);
            }
            if (existe >= 1) {
                sicaderEdoCtaFuturosSave.getSicaderEdoCtaFuturosDetalles()
                        .stream()
                        .filter(lista -> lista.getSocioId() == cartaConfirmacionRequest.getIdSocioLiquidador())
                        .forEach(lista ->
                        {
                            lista.setEndingBalance(cartaConfirmacionRequest.getMontoBalance());
                            lista.setIvaTax(cartaConfirmacionRequest.getMontoIva());
                        });
                if (existe >= 1) {
                    sicaderEdoCtaFuturosSave.getSicaderEdoCtaFuturosDetalles()
                            .stream()
                            .filter(lista -> lista.getSocioId() == cartaConfirmacionRequest.getIdSocioLiquidador())
                            .forEach(lista ->
                            {
                                lista.setEndingBalance(cartaConfirmacionRequest.getMontoBalance());
                                lista.setIvaTax(cartaConfirmacionRequest.getMontoIva());
                            });

                }

            } else {
                sicaderEdoCtaFuturosDetalle.setEndingBalance(cartaConfirmacionRequest.getMontoBalance());
                sicaderEdoCtaFuturosDetalle.setIvaTax(cartaConfirmacionRequest.getMontoIva());
                sicaderEdoCtaFuturosDetalle.setSocioId(cartaConfirmacionRequest.getIdSocioLiquidador());
                sicaderEdoCtaFuturosDetalle.setSicaderEdoCtaFuturos(sicaderEdoCtaFuturosSave);
                sicaderEdoCtaFuturosSave.getSicaderEdoCtaFuturosDetalles().add(sicaderEdoCtaFuturosDetalle);
            }
            guardaSicaderEdoCtaFuturosExiste(sicaderEdoCtaFuturosSave);
        } else {
            sicaderEdoCtaFuturosSave.setFechaOperacion(cartaConfirmacionRequest.getFechaOperacion());
            sicaderEdoCtaFuturosSave.setUsuRegistro(cartaConfirmacionRequest.getUsuario());
            sicaderEdoCtaFuturosSave.setFechaRegistro(Constants.diaActual);
            sicaderEdoCtaFuturosDetalle.setEndingBalance(cartaConfirmacionRequest.getMontoBalance());
            sicaderEdoCtaFuturosDetalle.setIvaTax(cartaConfirmacionRequest.getMontoIva());
            sicaderEdoCtaFuturosDetalle.setSocioId(cartaConfirmacionRequest.getIdSocioLiquidador());
            sicaderEdoCtaFuturosDetalle.setSicaderEdoCtaFuturos(sicaderEdoCtaFuturosSave);
            guardaSicaderEdoCtaFuturos(sicaderEdoCtaFuturosSave, sicaderEdoCtaFuturosDetalle);
        }

        return new ResponseDto("OK");
    }

    @Override
    public ResponseDto CargaCuentaAsigna(CuentaAsignaRequest cuentaAsignaRequest) {
        Optional<SicaderEdoCtaAsigna> sicaderEdoCtaAsigna = sicaderEdoCtaAsignaRepository.findByFechaOperacion(cuentaAsignaRequest.getFechaOperacion());
        SicaderEdoCtaAsigna sicaderEdoCtaAsignaSave = new SicaderEdoCtaAsigna();
        SicaderEdoCtaAsignaDetalle sicaderEdoCtaAsignaDetalle = new SicaderEdoCtaAsignaDetalle();
        if (sicaderEdoCtaAsigna.isPresent()) {
            sicaderEdoCtaAsignaSave = sicaderEdoCtaAsigna.get();
            sicaderEdoCtaAsignaSave.setFechaModificacion(Constants.diaActual);
            sicaderEdoCtaAsignaSave.setUsuModificacion(cuentaAsignaRequest.getUsuario());
            long existe = sicaderEdoCtaAsignaSave.getSicaderEdoCtaAsignaDetalle()
                    .stream()
                    .filter(lista -> lista.getSocioId() == cuentaAsignaRequest.getIdSocioLiquidador())
                    .count();
            if (existe >= 1 && !cuentaAsignaRequest.isForzar()) {
                return new ResponseDto(Constants.MENSAJE_EXISTE);
            }
            if (existe >= 1) {
                sicaderEdoCtaAsignaSave.getSicaderEdoCtaAsignaDetalle()
                        .stream()
                        .filter(lista -> lista.getSocioId() == cuentaAsignaRequest.getIdSocioLiquidador())
                        .forEach(lista ->
                        {
                            lista.setBalanceFinal(cuentaAsignaRequest.getBalanceFinal());
                            lista.setIvaComOperacion(cuentaAsignaRequest.getIvaOperacion());
                            lista.setIvaComManejoCta(cuentaAsignaRequest.getIvaCuenta());
                        });
            } else {
                sicaderEdoCtaAsignaDetalle.setSocioId(cuentaAsignaRequest.getIdSocioLiquidador());
                sicaderEdoCtaAsignaDetalle.setBalanceFinal(cuentaAsignaRequest.getBalanceFinal());
                sicaderEdoCtaAsignaDetalle.setIvaComManejoCta(cuentaAsignaRequest.getIvaCuenta());
                sicaderEdoCtaAsignaDetalle.setIvaComOperacion(cuentaAsignaRequest.getIvaOperacion());
                sicaderEdoCtaAsignaDetalle.setSicaderEdoCtaAsigna(sicaderEdoCtaAsignaSave);
                sicaderEdoCtaAsignaSave.getSicaderEdoCtaAsignaDetalle().add(sicaderEdoCtaAsignaDetalle);
            }
            guardaSicaderEdoCtaAsignaExiste(sicaderEdoCtaAsignaSave);
        } else {
            sicaderEdoCtaAsignaSave.setFechaOperacion(cuentaAsignaRequest.getFechaOperacion());
            sicaderEdoCtaAsignaSave.setUsuRegistro(cuentaAsignaRequest.getUsuario());
            sicaderEdoCtaAsignaSave.setFechaRegistro(Constants.diaActual);
            sicaderEdoCtaAsignaDetalle.setSocioId(cuentaAsignaRequest.getIdSocioLiquidador());
            sicaderEdoCtaAsignaDetalle.setBalanceFinal(cuentaAsignaRequest.getBalanceFinal());
            sicaderEdoCtaAsignaDetalle.setIvaComManejoCta(cuentaAsignaRequest.getIvaCuenta());
            sicaderEdoCtaAsignaDetalle.setIvaComOperacion(cuentaAsignaRequest.getIvaOperacion());
            sicaderEdoCtaAsignaDetalle.setSicaderEdoCtaAsigna(sicaderEdoCtaAsignaSave);
            guardaSicaderEdoCtaAsigna(sicaderEdoCtaAsignaSave, sicaderEdoCtaAsignaDetalle);
        }
        return new ResponseDto("OK");
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAllCartaConfirmacion(int page, int size) {
        List<SicaderEdoCtaFuturos> sicaderEdoCtaFuturos = new ArrayList<>();
        Pageable paging =
                PageRequest.of(page, size);
        Page<SicaderEdoCtaFuturos> pageSicaderEdoCtaFuturos = sicaderEdoCtaFuturosRepository.findAll((org.springframework.data.domain.Pageable) paging);
        sicaderEdoCtaFuturos = pageSicaderEdoCtaFuturos.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("sicaderEdoCtaFuturos", sicaderEdoCtaFuturos);
        response.put("totalPaginas", pageSicaderEdoCtaFuturos.getTotalPages());
        response.put("totalRegistros", pageSicaderEdoCtaFuturos.getTotalElements());
        response.put("paginaActual", pageSicaderEdoCtaFuturos.getNumber());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public List<CartaConfirmacionDto> getAllCartaConfirmacion(LocalDate fechaOperacion) {
        List<Object[]> objectList = sicaderEdoCtaFuturosRepository.getSicaderEdoCtaFuturos(fechaOperacion);
        List<CartaConfirmacionDto> cartaConfirmacionDtos = new ArrayList<>();
        cartaConfirmacionDtos.addAll(
                objectList.stream()
                        .map(ob -> new CartaConfirmacionDto(ob))
                        .collect(Collectors.toList()));
        return cartaConfirmacionDtos;
    }

    @Override
    public List<CuentaAsignaDto> getAllCuentaAsigna(LocalDate fechaOperacion) {
        List<Object[]> objectList = sicaderEdoCtaAsignaRepository.getSicaderCtaAsigna(fechaOperacion);
        List<CuentaAsignaDto> cuentaAsignaDtos = new ArrayList<>();
        cuentaAsignaDtos.addAll(
                objectList.stream()
                        .map(ob -> new CuentaAsignaDto(ob))
                        .collect(Collectors.toList()));
        return cuentaAsignaDtos;
    }

    @Transactional
    public void guardaSicaderEdoCtaFuturos(SicaderEdoCtaFuturos sicaderEdoCtaFuturos, SicaderEdoCtaFuturosDetalle sicaderEdoCtaFuturosDetalle) {
        sicaderEdoCtaFuturos = sicaderEdoCtaFuturosRepository.saveAndFlush(sicaderEdoCtaFuturos);
        sicaderEdoCtaFuturosDetalleRepository.save(sicaderEdoCtaFuturosDetalle);

    }

    @Transactional
    public void guardaSicaderEdoCtaFuturosExiste(SicaderEdoCtaFuturos sicaderEdoCtaFuturos) {
        sicaderEdoCtaFuturos = sicaderEdoCtaFuturosRepository.saveAndFlush(sicaderEdoCtaFuturos);
        //sicaderEdoCtaFuturosDetalleRepository.saveAll(sicaderEdoCtaFuturosDetalle);

    }

    @Transactional
    public void guardaSicaderEdoCtaAsigna(SicaderEdoCtaAsigna sicaderEdoCtaAsignaSave, SicaderEdoCtaAsignaDetalle sicaderEdoCtaAsignaDetalle) {
        sicaderEdoCtaAsignaSave = sicaderEdoCtaAsignaRepository.saveAndFlush(sicaderEdoCtaAsignaSave);
        sicaderEdoCtaAsignaDetalleRepository.save(sicaderEdoCtaAsignaDetalle);
    }

    @Transactional
    public void guardaSicaderEdoCtaAsignaExiste(SicaderEdoCtaAsigna sicaderEdoCtaAsignaSave) {
        sicaderEdoCtaAsignaSave = sicaderEdoCtaAsignaRepository.saveAndFlush(sicaderEdoCtaAsignaSave);
    }

}
