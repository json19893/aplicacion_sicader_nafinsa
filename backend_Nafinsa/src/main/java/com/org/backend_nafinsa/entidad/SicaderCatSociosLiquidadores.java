package com.org.backend_nafinsa.entidad;

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
@Table(schema = "SICADER", name = "SICADER_CAT_SOCIOS_LIQUIDADORES")
public class SicaderCatSociosLiquidadores {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SICADER_CAT_SOC_LIQ_SEQ")
    @SequenceGenerator(schema = "SICADER", name = "SICADER_CAT_SOC_LIQ_SEQ", sequenceName = "SICADER_CAT_SOC_LIQ_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;


}
