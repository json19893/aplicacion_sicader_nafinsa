package com.org.backend_nafinsa.service.impl;

import com.org.backend_nafinsa.dto.*;
import com.org.backend_nafinsa.entidad.*;
import com.org.backend_nafinsa.repository.*;
import com.org.backend_nafinsa.service.DmtaService;
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
public class DmtaServiceImpl implements DmtaService {
    @Autowired
    SicaderCompBanxicoRepository sicaderCompBanxicoRepository;
    @Autowired
    SicaderCompBanxicoDetalleRepository sicaderCompBanxicoDetalleRepository;

    @Autowired
    SicaderInteresesMargenRepository sicaderInteresesMargenRepository;

    @Autowired
    SicaderInteresesMargenDetalleRepository sicaderInteresesMargenDetalleRepository;

    @Autowired
    SicaderC10Repository sicaderC10Repository;

    @Autowired
    SicaderC10DetalleRepository sicaderC10DetalleRepository;

    @Override
    public ResponseDto cargaValuacionBanxico(ComplementoBanxicoRequest complementoBanxicoRequest) {
        Optional<SicaderCompBanxico> sicaderCompBanxico = sicaderCompBanxicoRepository.findByFechaOperacion(complementoBanxicoRequest.getFechaOperacion());
        SicaderCompBanxico sicaderCompBanxicoSave = new SicaderCompBanxico();
        SicaderCompBanxicoDetalle sicaderCompBanxicoDetalle = new SicaderCompBanxicoDetalle();
        if (sicaderCompBanxico.isPresent() && !complementoBanxicoRequest.isForzar()) {
            return new ResponseDto(Constants.MENSAJE_EXISTE);
        }
        if (sicaderCompBanxico.isPresent()) {
            sicaderCompBanxicoSave = sicaderCompBanxico.get();
            sicaderCompBanxicoSave.setUsuModificacion(complementoBanxicoRequest.getUsuario());
            sicaderCompBanxicoSave.setFechaModificacion(Constants.diaActual);
            sicaderCompBanxicoSave.getSicaderCompBanxicoDetalles().clear();
        } else {
            sicaderCompBanxicoSave.setFechaOperacion(complementoBanxicoRequest.getFechaOperacion());
            sicaderCompBanxicoSave.setUsuRegistro(complementoBanxicoRequest.getUsuario());
            sicaderCompBanxicoSave.setFechaRegistro(Constants.diaActual);
        }
        sicaderCompBanxicoDetalle.setValbanxico(complementoBanxicoRequest.getValuacionBanxico());
        guardaSicaderCompBanxico(sicaderCompBanxicoSave, sicaderCompBanxicoDetalle);
        return new ResponseDto("OK");
    }

    @Override
    public ResponseDto cargaCuentaMargen(CuentaMargenRequest cuentaMargenRequest) {
        Optional<SicaderInteresesMargen> sicaderInteresesMargen = sicaderInteresesMargenRepository.findByFechaOperacion(cuentaMargenRequest.getFechaOperacion());
        SicaderInteresesMargen sicaderInteresesMargenSave = new SicaderInteresesMargen();
        SicaderInteresesMargenDetalle sicaderInteresesMargenDetalle = new SicaderInteresesMargenDetalle();
        if (sicaderInteresesMargen.isPresent()) {
            sicaderInteresesMargenSave = sicaderInteresesMargen.get();
            sicaderInteresesMargenSave.setUsuModificacion(cuentaMargenRequest.getUsuario());
            sicaderInteresesMargenSave.setFechaModificacion(Constants.diaActual);
            long existe = sicaderInteresesMargenSave.getSicaderInteresesMargenDetalle()
                    .stream()
                    .filter(lista -> lista.getContraparteId() == cuentaMargenRequest.getContraparteId())
                    .count();
            if (existe >= 1 && !cuentaMargenRequest.isForzar()) {
                return new ResponseDto(Constants.MENSAJE_EXISTE);
            }
            if (existe >= 1) {
                sicaderInteresesMargenSave.getSicaderInteresesMargenDetalle()
                        .stream()
                        .filter(lista -> lista.getContraparteId() == cuentaMargenRequest.getContraparteId())
                        .forEach(lista ->
                        {
                            lista.setEgresos (cuentaMargenRequest.getEgresos());
                            lista.setIngresos(cuentaMargenRequest.getIngresos());

                        });
            } else {
                sicaderInteresesMargenDetalle.setContraparteId(cuentaMargenRequest.getContraparteId());
                sicaderInteresesMargenDetalle.setEgresos(cuentaMargenRequest.getEgresos());
                sicaderInteresesMargenDetalle.setIngresos(cuentaMargenRequest.getIngresos());
                sicaderInteresesMargenDetalle.setSicaderInteresesMargen(sicaderInteresesMargenSave);
                sicaderInteresesMargenSave.getSicaderInteresesMargenDetalle().add(sicaderInteresesMargenDetalle);
            }
            guardaSicaderInteresesMargenExiste(sicaderInteresesMargenSave);
        } else {
            sicaderInteresesMargenSave.setFechaOperacion(cuentaMargenRequest.getFechaOperacion());
            sicaderInteresesMargenSave.setFechaRegistro(Constants.diaActual);
            sicaderInteresesMargenSave.setUsuRegistro(cuentaMargenRequest.getUsuario());
            sicaderInteresesMargenDetalle.setEgresos(cuentaMargenRequest.getEgresos());
            sicaderInteresesMargenDetalle.setContraparteId(cuentaMargenRequest.getContraparteId());
            sicaderInteresesMargenDetalle.setIngresos(cuentaMargenRequest.getIngresos());
            guardaSicaderInteresesMargen(sicaderInteresesMargenSave, sicaderInteresesMargenDetalle);
        }
        return new ResponseDto("OK");
    }

    @Override
    public ResponseDto cargaSicaderC10(SicaderC10Request sicaderC10Request) {
        Optional<SicaderC10> sicaderC10 = sicaderC10Repository.findByFechaOperacion(sicaderC10Request.getMesAnio());
        SicaderC10 sicaderC10Save = new SicaderC10();
        List<SicaderC10Detalle> sicaderC10Detalle = new ArrayList<>();

        if (sicaderC10.isPresent()) {
            sicaderC10Save = sicaderC10.get();
            sicaderC10Save.setFechaOperacion(sicaderC10Request.getMesAnio());
            sicaderC10Save.setUsuModificacion(sicaderC10Request.getUsuario());
            sicaderC10Save.setFechaModificacion(Constants.diaActual);
            long existe = sicaderC10Save.getSicaderC10Detalles()
                    .stream()
                    .filter(lista -> lista.getCoberturaId() == sicaderC10Request.getCoberturaId())
                    .count();
            if (existe >= 1 && !sicaderC10Request.isForzar()) {
                return new ResponseDto(Constants.MENSAJE_EXISTE);
            }
            if (existe >= 1) {
                List<Double> registrosActualizados = new ArrayList<>();
                List<SicaderC10SubListRequest> listaActualizar = sicaderC10Request.getSicaderC10SubList();
                sicaderC10Save.getSicaderC10Detalles()
                        .stream()
                        .filter(lista -> lista.getCoberturaId() == sicaderC10Request.getCoberturaId())
                        .forEach(lista ->
                        {
                            for (SicaderC10SubListRequest r :
                                    listaActualizar) {
                                if (lista.getTipoCuentaId() == r.getTipoCuentaId()) {
                                    lista.setImporte(r.getImporte());
                                    registrosActualizados.add(r.getTipoCuentaId());
                                }
                            }
                        });
                for (SicaderC10SubListRequest agregaFaltantes :
                        listaActualizar) {
                    SicaderC10Detalle addC10Detalle = new SicaderC10Detalle();
                    if (!registrosActualizados.contains(agregaFaltantes.getTipoCuentaId())) {
                        addC10Detalle.setImporte(agregaFaltantes.getImporte());
                        addC10Detalle.setTipoCuentaId(agregaFaltantes.getTipoCuentaId());
                        addC10Detalle.setCoberturaId(sicaderC10Request.getCoberturaId());
                        addC10Detalle.setSicaderC10(sicaderC10Save);
                        sicaderC10Detalle.add(addC10Detalle);
                    }
                }
                guardarSciaderC10Existe(sicaderC10Save, sicaderC10Detalle);
            }else{
                sicaderC10Save.setFechaOperacion(sicaderC10Request.getMesAnio());
                sicaderC10Save.setUsuRegistro(sicaderC10Request.getUsuario());
                sicaderC10Save.setFechaRegistro(Constants.diaActual);
                for (SicaderC10SubListRequest agregaFaltantes :
                        sicaderC10Request.getSicaderC10SubList()) {
                    SicaderC10Detalle addC10Detalle = new SicaderC10Detalle();
                    addC10Detalle.setImporte(agregaFaltantes.getImporte());
                    addC10Detalle.setTipoCuentaId(agregaFaltantes.getTipoCuentaId());
                    addC10Detalle.setCoberturaId(sicaderC10Request.getCoberturaId());
                    addC10Detalle.setSicaderC10(sicaderC10Save);
                    sicaderC10Detalle.add(addC10Detalle);
                }
                guardarSciaderC10Existe(sicaderC10Save, sicaderC10Detalle);
            }
        } else {
            sicaderC10Save.setFechaOperacion(sicaderC10Request.getMesAnio());
            sicaderC10Save.setUsuRegistro(sicaderC10Request.getUsuario());
            sicaderC10Save.setFechaRegistro(Constants.diaActual);
            for (SicaderC10SubListRequest agregaFaltantes :
                    sicaderC10Request.getSicaderC10SubList()) {
                SicaderC10Detalle addC10Detalle = new SicaderC10Detalle();
                    addC10Detalle.setImporte(agregaFaltantes.getImporte());
                    addC10Detalle.setTipoCuentaId(agregaFaltantes.getTipoCuentaId());
                    addC10Detalle.setCoberturaId(sicaderC10Request.getCoberturaId());
                    sicaderC10Detalle.add(addC10Detalle);
            }
            guardarSciaderC10(sicaderC10Save, sicaderC10Detalle);
        }
        return new ResponseDto("OK");
    }

    public List<SicaderC10Detalle> obtenerDetalleList(SicaderC10Request sicaderC10Request) {
        List<SicaderC10Detalle> sicaderC10Detalle = new ArrayList<>();
        for (SicaderC10SubListRequest lista :
                sicaderC10Request.getSicaderC10SubList()) {
            SicaderC10Detalle sicaderC10Detalle1 = new SicaderC10Detalle();
            sicaderC10Detalle1.setImporte(lista.getImporte());
            sicaderC10Detalle1.setCoberturaId(sicaderC10Request.getCoberturaId());
            sicaderC10Detalle1.setTipoCuentaId(lista.getTipoCuentaId());
            sicaderC10Detalle.add(sicaderC10Detalle1);
        }
        return sicaderC10Detalle;
    }


    @Override
    public List<BanxicoDTO> getAllSicaderBanxico(LocalDate fechaOperacion) {
        List<Object[]> objectList = sicaderCompBanxicoRepository.getSicaderBanxico(fechaOperacion);
        List<BanxicoDTO> banxicoDTOS = new ArrayList<>();
        banxicoDTOS.addAll(
                objectList.stream()
                        .map(ob -> new BanxicoDTO(ob))
                        .collect(Collectors.toList()));

        return banxicoDTOS;
    }

    @Override
    public List<InteresGeneradoMargenDto> getAllSicaderInteresesMargen(LocalDate fechaOperacion) {

        List<Object[]> objectList = sicaderInteresesMargenRepository.getSicaderInteresesMargen(fechaOperacion);
        List<InteresGeneradoMargenDto> interesGeneradoMargenDtos = new ArrayList<>();
        interesGeneradoMargenDtos.addAll(
                objectList.stream()
                        .map(ob -> new InteresGeneradoMargenDto(ob))
                        .collect(Collectors.toList()));
        return interesGeneradoMargenDtos;
    }

    @Override
    public List<C10ReporteDto> getAllSicaderC10(LocalDate fechaOperacion) {
        List<Object[]> objectList = sicaderC10Repository.getAllSicaderC10(fechaOperacion);
        List<C10ReporteDto> c10ReporteDtos = new ArrayList<>();
        c10ReporteDtos.addAll(
                objectList.stream()
                        .map(ob -> new C10ReporteDto(ob))
                        .collect(Collectors.toList()));
        return c10ReporteDtos;

    }

    @Transactional
    public void guardaSicaderCompBanxico(SicaderCompBanxico sicaderCompBanxico, SicaderCompBanxicoDetalle sicaderCompBanxicoDetalle) {
        sicaderCompBanxico = sicaderCompBanxicoRepository.saveAndFlush(sicaderCompBanxico);
        sicaderCompBanxicoDetalle.setSicaderCompBanxico(sicaderCompBanxico);
        sicaderCompBanxicoDetalleRepository.save(sicaderCompBanxicoDetalle);

    }


    @Transactional
    public void guardaSicaderInteresesMargenExiste(SicaderInteresesMargen sicaderInteresesMargenSave) {
        sicaderInteresesMargenSave = sicaderInteresesMargenRepository.saveAndFlush(sicaderInteresesMargenSave);
    }

    @Transactional
    public void guardaSicaderInteresesMargen(SicaderInteresesMargen sicaderInteresesMargenSave, SicaderInteresesMargenDetalle sicaderInteresesMargenDetalle) {
        sicaderInteresesMargenSave = sicaderInteresesMargenRepository.saveAndFlush(sicaderInteresesMargenSave);
        sicaderInteresesMargenDetalle.setSicaderInteresesMargen(sicaderInteresesMargenSave);
        sicaderInteresesMargenDetalleRepository.save(sicaderInteresesMargenDetalle);
    }

    @Transactional
    public void guardarSciaderC10Existe(SicaderC10 sicaderC10Save, List<SicaderC10Detalle> sicaderC10Detalle) {
        sicaderC10Save = sicaderC10Repository.saveAndFlush(sicaderC10Save);
        if (sicaderC10Detalle != null) {
            sicaderC10DetalleRepository.saveAllAndFlush(sicaderC10Detalle);
        }


    }

    @Transactional
    public void guardarSciaderC10(SicaderC10 sicaderC10Save, List<SicaderC10Detalle> sicaderC10Detalle) {
        sicaderC10Save = sicaderC10Repository.saveAndFlush(sicaderC10Save);
        SicaderC10 finalSicaderC10Save = sicaderC10Save;
        sicaderC10Detalle.forEach(r -> r.setSicaderC10(finalSicaderC10Save));
        sicaderC10DetalleRepository.saveAllAndFlush(sicaderC10Detalle);
    }
}
