package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchivoMensualJsDto {

    private String socio;
    ArrayList<Object[]> data = new ArrayList<Object[]>();

}
