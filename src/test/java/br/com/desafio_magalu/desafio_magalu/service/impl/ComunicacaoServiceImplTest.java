package br.com.desafio_magalu.desafio_magalu.service.impl;

import br.com.desafio_magalu.desafio_magalu.controller.dto.request.ComunicacaoRequest;
import br.com.desafio_magalu.desafio_magalu.model.Comunicacao;
import br.com.desafio_magalu.desafio_magalu.model.enums.FormatoComunicacao;
import br.com.desafio_magalu.desafio_magalu.model.enums.Status;
import br.com.desafio_magalu.desafio_magalu.repository.ComunicacaoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static br.com.desafio_magalu.desafio_magalu.model.enums.Status.CANCELADO;
import static br.com.desafio_magalu.desafio_magalu.model.enums.Status.PENDENTE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ComunicacaoServiceImplTest {

    private AutoCloseable closeable;
    private ComunicacaoRequest request;
    private Comunicacao comunicacao;

    @Mock
    private ComunicacaoRepository repository;

    @InjectMocks
    private ComunicacaoServiceImpl service;


    @BeforeEach
    void setUp() {
      this. closeable = MockitoAnnotations.openMocks(this);
        this.request = new ComunicacaoRequest(
                LocalDateTime.now(),
                "Fulano",
                "Essa é a mensagem",
                "EMAIL" );

        this.comunicacao = new Comunicacao(
                request.dataHora(),
                request.destinatario(),
                request.mensagem(),
                FormatoComunicacao.valueOf(request.formatoComunicacao())
        );
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void criar_agendamento_com_sucesso() {

        when(repository.save(any(Comunicacao.class))).thenReturn(this.comunicacao);

        var response = service.agendar(request);

        assertNotNull(response);
        verify(repository, times(1)).save(any(Comunicacao.class));
    }

    @Test
    void deve_cancelar_um_agendamento_com_sucesso() {
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(this.comunicacao));

        service.cancelar(1L);

        assertEquals(CANCELADO, comunicacao.getStatus());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void deve_consultar_um_agendamento_com_sucesso() {
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(this.comunicacao));

        service.consultar(1L);

        assertEquals(PENDENTE, comunicacao.getStatus());
        verify(repository, times(1)).findById(1L);
    }
}