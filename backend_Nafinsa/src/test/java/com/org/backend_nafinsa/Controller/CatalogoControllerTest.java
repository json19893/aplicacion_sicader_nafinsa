package com.org.backend_nafinsa.Controller;

import com.org.backend_nafinsa.controller.CatalogoController;
import com.org.backend_nafinsa.service.CatalogoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CatalogoControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    private CatalogoController catalogoController;

    @Mock
    CatalogoService catalogoService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(catalogoController).build();
    }

    @Test
    public void sicaderGetSocioLiquidador() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sicader/catalogo/getSocioLiquidador", "/sicader/catalogo/getTipoCuenta", true)
                .accept(MediaType.APPLICATION_JSON);
        this.mockMvc
                .perform(requestBuilder)
                .andExpectAll(status().isOk())
                .andReturn();
    }

    @Test
    public void sicaderGetTipoCuenta() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sicader/catalogo/getTipoCuenta", "/sicader/catalogo/getTipoCuenta", true)
                .accept(MediaType.APPLICATION_JSON);
        this.mockMvc
                .perform(requestBuilder)
                .andExpectAll(status().isOk())
                .andReturn();
    }

    @Test
    public void sicaderGetTipoDerivado() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sicader/catalogo/getTipoDerivado", "/sicader/catalogo/getTipoCuenta", true)
                .accept(MediaType.APPLICATION_JSON);
        this.mockMvc
                .perform(requestBuilder)
                .andExpectAll(status().isOk())
                .andReturn();
    }

    @Test
    public void sicaderGetContraparte() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sicader/catalogo/getContraparte", "/sicader/catalogo/getTipoCuenta", true)
                .accept(MediaType.APPLICATION_JSON);
        this.mockMvc
                .perform(requestBuilder)
                .andExpectAll(status().isOk())
                .andReturn();
    }

}
