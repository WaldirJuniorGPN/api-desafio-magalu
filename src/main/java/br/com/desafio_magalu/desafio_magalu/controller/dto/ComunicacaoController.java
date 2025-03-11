package br.com.desafio_magalu.desafio_magalu.controller.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comunicacao")
@RequiredArgsConstructor
public class ComunicacaoController {


    private ComunicacaoService service;
}
