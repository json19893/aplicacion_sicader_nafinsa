package com.org.backend_nafinsa.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "SICADER", name = "SICADER_CON_EJECUCIONES")
public class SicaderConEjecuciones {
    @Id
    @Column(name = "ID")
    private Long id;


    @Column(name = "TIPO_CONCILIACION")
    private String tipoconciliacion;




}
