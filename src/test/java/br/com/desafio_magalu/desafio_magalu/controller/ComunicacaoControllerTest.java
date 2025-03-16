package br.com.desafio_magalu.desafio_magalu.controller;

import br.com.desafio_magalu.desafio_magalu.controller.dto.request.ComunicacaoRequest;
import br.com.desafio_magalu.desafio_magalu.controller.dto.response.ComunicacaoResponseId;
import br.com.desafio_magalu.desafio_magalu.controller.dto.response.ComunicacaoResponseStatus;
import br.com.desafio_magalu.desafio_magalu.model.enums.Status;
import br.com.desafio_magalu.desafio_magalu.service.ComunicacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ComunicacaoControllerTest {

    private MockMvc mockMvc;
    private AutoCloseable closeable;
    private ObjectMapper objectMapper;

    @Mock
    private ComunicacaoService service;

    @InjectMocks
    private ComunicacaoController controller;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.closeable.close();
    }

    @Test
    void deve_agendar_comunicacao_com_sucesso() throws Exception {
        var request = new ComunicacaoRequest(
                LocalDateTime.now().plusMinutes(2),
                "destinatario@exemplo.com",
                "mensagem de teste",
                "EMAIL"
        );

        var response = new ComunicacaoResponseId(1L);
        when(this.service.agendar(any(ComunicacaoRequest.class))).thenReturn(response);

        this.mockMvc.perform(post("/comunicacao/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/1"));

        verify(this.service, times(1)).agendar(any(ComunicacaoRequest.class));
    }

    @Test
    void deve_consultar_status_comunicacao_com_sucesso() throws Exception {
        Long id = 1L;
        ComunicacaoResponseStatus responseStatus = new ComunicacaoResponseStatus(Status.AGENDADO);
        when(this.service.consultar(id)).thenReturn(responseStatus);

        this.mockMvc.perform(get("/comunicacao/consulta/{id}", id.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("AGENDADO"));

        verify(this.service, times(1)).consultar(id);
    }

    @Test
    void deve_cancelar_comunicacao_com_sucesso() throws Exception {
        Long id = 1L;
        doNothing().when(this.service).cancelar(id);
        this.mockMvc.perform(delete("/comunicacao/cancelamento/{id}", id))
                .andExpect(status().isNoContent());

        verify(this.service, times(1)).cancelar(id);
    }
}