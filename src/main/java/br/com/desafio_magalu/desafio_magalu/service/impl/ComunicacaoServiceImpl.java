package br.com.desafio_magalu.desafio_magalu.service.impl;

import br.com.desafio_magalu.desafio_magalu.controller.dto.request.ComunicacaoRequest;
import br.com.desafio_magalu.desafio_magalu.controller.dto.response.ComunicacaoResponseId;
import br.com.desafio_magalu.desafio_magalu.controller.dto.response.ComunicacaoResponseStatus;
import br.com.desafio_magalu.desafio_magalu.model.Comunicacao;
import br.com.desafio_magalu.desafio_magalu.model.enums.FormatoComunicacao;
import br.com.desafio_magalu.desafio_magalu.model.enums.Status;
import br.com.desafio_magalu.desafio_magalu.repository.ComunicacaoRepository;
import br.com.desafio_magalu.desafio_magalu.service.ComunicacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComunicacaoServiceImpl implements ComunicacaoService {

    private final ComunicacaoRepository repository;

    @Override
    public ComunicacaoResponseId agendar(ComunicacaoRequest request) {
        var comunicacao = criarObjeto(request);
        repository.save(comunicacao);

        return new ComunicacaoResponseId(comunicacao.getId());
    }

    @Override
    public void cancelar(Long id) {

    }

    @Override
    public ComunicacaoResponseStatus consultar(Long id) {
        return null;
    }

    private Comunicacao criarObjeto(ComunicacaoRequest request) {
        return new Comunicacao(request.dataHora(),
                request.destinatario(),
                request.mensagem(),
                FormatoComunicacao.valueOf(request.formatoComunicacao().toUpperCase()),
                Status.ENVIADO);
    }
}
