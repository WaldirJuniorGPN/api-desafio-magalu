package br.com.desafio_magalu.desafio_magalu.controller;

import br.com.desafio_magalu.desafio_magalu.controller.dto.ComunicacaoRequest;
import br.com.desafio_magalu.desafio_magalu.controller.dto.ComunicacaoResponse;
import br.com.desafio_magalu.desafio_magalu.controller.dto.ComunicacaoResponseStatus;
import br.com.desafio_magalu.desafio_magalu.service.ComunicacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/comunicacao")
@RequiredArgsConstructor
public class ComunicacaoController {

    private final ComunicacaoService service;

    @PostMapping("/agendamento")
    public ResponseEntity<ComunicacaoResponse> agendamento(@RequestBody @Valid ComunicacaoRequest request, UriComponentsBuilder uriComponentsBuilder) {
        var response = service.agendar(request);
        var uri = uriComponentsBuilder.path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/consulta/{id}")
    public ResponseEntity<ComunicacaoResponseStatus> consulta(@PathVariable Long id) {
        var response = service.consultar(id);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/cancelamento/{id}")
    public ResponseEntity<Void> cancelamento(@PathVariable Long id) {
        service.cancelar(id);

        return ResponseEntity.noContent().build();
    }
}
