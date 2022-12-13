package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.dto.ConciliaPkg;
import com.org.backend_nafinsa.dto.SalidaPkg;
import com.org.backend_nafinsa.dto.ValidacionPkg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDate;
import java.util.Date;

@Repository
public class CallPkgSicader {
    private final EntityManager entityManager;

    @Autowired
    public CallPkgSicader(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public SalidaPkg SicaderConciliaPkg(ConciliaPkg conciliaPkg) {

        StoredProcedureQuery sicaderConciliaPkg = entityManager.createStoredProcedureQuery
                ("SICADER.SICADER_CONCILIA_PKG.CONCILIA");
        sicaderConciliaPkg.registerStoredProcedureParameter("P_TIPO_CONCILIA", String.class, ParameterMode.IN);
        sicaderConciliaPkg.registerStoredProcedureParameter("P_FECHA", LocalDate.class, ParameterMode.IN);
        sicaderConciliaPkg.registerStoredProcedureParameter("P_USUARIO", String.class, ParameterMode.IN);
        sicaderConciliaPkg.registerStoredProcedureParameter("P_DERIVADO", Long.class, ParameterMode.IN);
        sicaderConciliaPkg.registerStoredProcedureParameter("P_ESTATUS_CONCILIA", String.class, ParameterMode.OUT);
        sicaderConciliaPkg.registerStoredProcedureParameter("P_MENSAJE", String.class, ParameterMode.OUT);
        sicaderConciliaPkg.setParameter("P_TIPO_CONCILIA", conciliaPkg.getInTipoConcilia());
        sicaderConciliaPkg.setParameter("P_FECHA", conciliaPkg.getInFecha());
        sicaderConciliaPkg.setParameter("P_USUARIO", conciliaPkg.getInUsuario());
        ((org.hibernate.procedure.ParameterRegistration) sicaderConciliaPkg.getParameter("P_DERIVADO")).enablePassingNulls(true);
        sicaderConciliaPkg.setParameter("P_DERIVADO", conciliaPkg.getInDerivado());
        sicaderConciliaPkg.execute();
        SalidaPkg salidaPkg = new SalidaPkg();
        salidaPkg.setEstatus((String) sicaderConciliaPkg.getOutputParameterValue("P_ESTATUS_CONCILIA"));
        salidaPkg.setMensaje((String) sicaderConciliaPkg.getOutputParameterValue("P_MENSAJE"));
        return salidaPkg;
    }

    public void SicaderValidaPkg(ValidacionPkg validacionPkg) {
        StoredProcedureQuery sicaderValidaPkg = entityManager.createStoredProcedureQuery
                ("SICADER.SICADER_VALIDACION_PKG.VALIDA_INSUMOS");
        sicaderValidaPkg.registerStoredProcedureParameter("P_FECHA", LocalDate.class, ParameterMode.IN);
        sicaderValidaPkg.registerStoredProcedureParameter("P_DERIVADO", Long.class, ParameterMode.IN);
        sicaderValidaPkg.registerStoredProcedureParameter("P_TIPO_CONCILIA", String.class, ParameterMode.IN);
        sicaderValidaPkg.setParameter("P_FECHA", validacionPkg.getFechaOperacion());
        ((org.hibernate.procedure.ParameterRegistration) sicaderValidaPkg.getParameter("P_DERIVADO")).enablePassingNulls(true);
        sicaderValidaPkg.setParameter("P_DERIVADO", validacionPkg.getDerivado());
        sicaderValidaPkg.setParameter("P_TIPO_CONCILIA", validacionPkg.getTipoConciliacion());
        sicaderValidaPkg.setHint( "hibernate.proc.param_null_passing.P_DERIVADO", "true" );
        sicaderValidaPkg.execute();
    }


}
