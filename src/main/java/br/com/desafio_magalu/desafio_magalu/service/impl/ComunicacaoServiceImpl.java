package br.com.desafio_magalu.desafio_magalu.service.impl;

import br.com.desafio_magalu.desafio_magalu.controller.dto.request.ComunicacaoRequest;
import br.com.desafio_magalu.desafio_magalu.controller.dto.response.ComunicacaoResponseId;
import br.com.desafio_magalu.desafio_magalu.controller.dto.response.ComunicacaoResponseStatus;
import br.com.desafio_magalu.desafio_magalu.model.Comunicacado;
import br.com.desafio_magalu.desafio_magalu.model.enums.FormatoComunicacao;
import br.com.desafio_magalu.desafio_magalu.repository.ComunicacaoRepository;
import br.com.desafio_magalu.desafio_magalu.service.ComunicacaoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.desafio_magalu.desafio_magalu.model.enums.Status.CANCELADO;
import static br.com.desafio_magalu.desafio_magalu.model.enums.Status.ENVIADO;

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
        var comunicacao = consultarNoBanco(id);
        comunicacao.setStatus(CANCELADO);
    }

    @Override
    public ComunicacaoResponseStatus consultar(Long id) {
        var comunicado = consultarNoBanco(id);

        return new ComunicacaoResponseStatus(comunicado.getStatus());
    }

    private Comunicacado criarObjeto(ComunicacaoRequest request) {
        return new Comunicacado(request.dataHora(),
                request.destinatario(),
                request.mensagem(),
                FormatoComunicacao.valueOf(request.formatoComunicacao().toUpperCase()),
                ENVIADO);
    }

    private Comunicacado consultarNoBanco(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Comunicado com ID: %d não foi encontrado", id)));
    }
}
