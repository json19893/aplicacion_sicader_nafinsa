package com.org.backend_nafinsa.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ResponseDto {

    private String respuesta;

    public ResponseDto(String respuesta) {
        this.respuesta = respuesta;
    }

}
