package br.com.unifap.pos.atv_sinc1.repository;

import br.com.unifap.pos.atv_sinc1.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    //Repositório para acesso ao banco de dados utilizado pela JPA. O acesso ao banco é gerenciado pelo ORM completamente.
}
