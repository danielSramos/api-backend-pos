package br.com.unifap.pos.atv_sinc1.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
@Data
public class User {
    /*
     * Representação da entidade no banco de dados. Aqui é onde criamos a tabela e seus atributos no banco de dados.
     * */
    @Id()
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    /*
    * mappedBy: configura o mapeamento para entidade forte da relação
    * cascade: configura o tipo de exclusão de dados quando deletado o usuário.
    * orphanRemoval: configura entidades que foram removidas da coleção ou dissociadas do relacionamento — devem ser automaticamente excluídos do banco de dados.
    * Esse atributo é o relacionamento entre as tabelas users e posts
    * */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    private String name;

    private String email;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
