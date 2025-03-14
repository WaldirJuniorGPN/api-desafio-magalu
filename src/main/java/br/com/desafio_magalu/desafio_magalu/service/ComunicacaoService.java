package br.com.desafio_magalu.desafio_magalu.service;

import br.com.desafio_magalu.desafio_magalu.controller.dto.request.ComunicacaoRequest;
import br.com.desafio_magalu.desafio_magalu.controller.dto.response.ComunicacaoResponseId;
import br.com.desafio_magalu.desafio_magalu.controller.dto.response.ComunicacaoResponseStatus;

public interface ComunicacaoService {

    ComunicacaoResponseId agendar(ComunicacaoRequest request);

    void cancelar(Long id);

    ComunicacaoResponseStatus consultar(Long id);
}
