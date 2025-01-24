package br.com.unifap.pos.atv_sinc1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter//cria todos os getters para os atributos
@Setter//cria todos os getters para os atributos
@AllArgsConstructor
public class PostDto {
    //DTO para manipulação de dados entre o banco de dados e o usuário

    private Long id;
    private String title;
    private String content;
    private String createdAt;

}
