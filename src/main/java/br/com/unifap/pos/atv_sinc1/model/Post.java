package br.com.unifap.pos.atv_sinc1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "posts")
@Data
public class Post {
    /*
    * Representação da entidade no banco de dados. Aqui é onde criamos a tabela e seus atributos no banco de dados.
    * */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    /*
    * fetch: configura o tipo de acesso ao dados no momento da consulta.
    * O tipo LAZY só utiliza o relacionamento(JOIN) caso os dados da outra tabela sejam solicitados explicitamente,
    * diminuindo o impacto nas consultas que não precisam retornar os dados do relacionamento em si.
    * Esse atributo representa o relacionamento entre as tabelas posts e users
    * */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String title;

    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
