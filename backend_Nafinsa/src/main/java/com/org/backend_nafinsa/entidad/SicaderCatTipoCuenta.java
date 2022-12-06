package com.org.backend_nafinsa.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "SICADER", name = "SICADER_CAT_TIPO_CUENTA")
public class SicaderCatTipoCuenta {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SICADER_CAT_TIPO_CTA_SEQ")
    @SequenceGenerator(schema = "SICADER", name = "SICADER_CAT_TIPO_CTA_SEQ", sequenceName = "SICADER_CAT_TIPO_CTA_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;


}
