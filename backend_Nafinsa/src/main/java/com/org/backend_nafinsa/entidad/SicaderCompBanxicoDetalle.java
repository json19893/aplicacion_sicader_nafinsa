package com.org.backend_nafinsa.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "SICADER", name = "SICADER_COMP_BANXICO_DETALLE")
public class SicaderCompBanxicoDetalle {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SICADER_COM_BAN_DET_SEQ")
    @SequenceGenerator(schema = "SICADER", name = "SICADER_COM_BAN_DET_SEQ", sequenceName = "SICADER_COM_BAN_DET_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "VAL_BANXICO")
    private double valbanxico;

    /*
    @Column(name = "REPORTE_ID")
    private Long reporteId;
    */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORTE_ID")
    private SicaderCompBanxico sicaderCompBanxico;

}
