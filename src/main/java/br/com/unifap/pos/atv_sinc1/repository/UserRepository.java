package br.com.unifap.pos.atv_sinc1.repository;

import br.com.unifap.pos.atv_sinc1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Repositório para acesso ao banco de dados utilizado pela JPA. O acesso ao banco é gerenciado pelo ORM completamente.

    //Método para buscar usuário pelo email e verificar se já existe. Retorna true ou false caso exista ou não. É usado para verificações na classe userService.
    boolean existsByEmail(String email);
}
