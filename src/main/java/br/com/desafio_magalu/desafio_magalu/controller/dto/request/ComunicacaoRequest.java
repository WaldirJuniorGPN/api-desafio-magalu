package br.com.desafio_magalu.desafio_magalu.controller.dto.request;

import br.com.desafio_magalu.desafio_magalu.model.enums.FormatoComunicacao;
import br.com.desafio_magalu.desafio_magalu.validation.ValidEnum;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ComunicacaoRequest(

        @NotNull(message = "A data/hora não pode estar em branco")
        @FutureOrPresent(message = "Não é permitido agendamentos com datas no passado")
        LocalDateTime dataHora,

        @NotBlank(message = "O destinatário não pdoe estar em branco")
        String destinatario,

        @NotBlank(message = "A mensagem não pode estar em branco")
        String mensagem,

        @NotNull(message = "O formato da comunicação é obrigatório.")
        @ValidEnum(enumClass = FormatoComunicacao.class, message = "Formato inválido. Use: EMAIL, SMS, PUSH ou WHATSAPP.")
        String formatoComunicacao
) {
}
