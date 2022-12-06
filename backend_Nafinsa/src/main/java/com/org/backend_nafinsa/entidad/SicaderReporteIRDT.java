package com.org.backend_nafinsa.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "SICADER", name = "SICADER_IRDT")
public class SicaderReporteIRDT {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SICADER_IRDT_SEQ")
    @SequenceGenerator(schema = "SICADER", name = "SICADER_IRDT_SEQ", sequenceName = "SICADER_IRDT_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "FECHA_OP")
    private LocalDate fechaOperacion;

    @Column(name = "FECHA_REGISTRO")
    private Date fechaRegistro;

    @Column(name = "USU_REGISTRO")
    private String usuRegistro;

    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;

    @Column(name = "USU_MODIFICACION")
    private String usuModificacion;

    @OneToMany(
            //mappedBy = "reporteId",
            mappedBy = "sicaderReporteIRDT",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<SicaderReporteIRDTDetalle> sicaderReporteIRDTDetalles;

    @Column(name = "ESTATUS")
    private String estatus;
}
