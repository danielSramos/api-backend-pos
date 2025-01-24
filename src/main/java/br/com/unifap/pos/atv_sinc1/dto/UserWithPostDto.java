package br.com.unifap.pos.atv_sinc1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter//cria todos os getters para os atributos
@Setter//cria todos os setters para os atributos
@AllArgsConstructor
public class UserWithPostDto {
    //DTO para manipulação de dados entre o banco de dados e o usuário

    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private List<PostDto> posts;

}
