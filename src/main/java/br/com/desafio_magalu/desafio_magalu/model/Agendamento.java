package br.com.desafio_magalu.desafio_magalu.model;

import br.com.desafio_magalu.desafio_magalu.model.enums.FormatoComunicacao;
import br.com.desafio_magalu.desafio_magalu.model.enums.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static br.com.desafio_magalu.desafio_magalu.model.enums.Status.PENDENTE;

@Entity(name = "Agendamento")
@Table(name = "agendamento")
@EqualsAndHashCode
@Setter
public class Agendamento {

    @Id
    @Getter
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "destinatario", nullable = false)
    private String destinatario;

    @Column(name = "mensagem", nullable = false)
    private String mensagem;

    @Enumerated(EnumType.STRING)
    @Column(name = "formato_comunicacao", nullable = false)
    private FormatoComunicacao formatoComunicacao;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @PrePersist
    public void prePersist() {
        this.status = PENDENTE;
    }
}

