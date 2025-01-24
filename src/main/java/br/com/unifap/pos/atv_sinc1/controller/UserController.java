package br.com.unifap.pos.atv_sinc1.controller;

import br.com.unifap.pos.atv_sinc1.dto.UserCreateRequest;
import br.com.unifap.pos.atv_sinc1.dto.UserDto;
import br.com.unifap.pos.atv_sinc1.dto.UserUpdateRequest;
import br.com.unifap.pos.atv_sinc1.dto.UserWithPostDto;
import br.com.unifap.pos.atv_sinc1.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    //Injeção de dependências para que o Spring gerencie a disponibilidade da classe service.
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Endpoint para listagem de todos os usuário com seus respectivos posts.
    @GetMapping
    public ResponseEntity<List<UserWithPostDto>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsersWithPosts());
    }

    //Endpoint para listagem de usuário pelo ID. Recebe o ID pela URL.
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    //Endpoint para criar um novo usuário. Recebe os dados no corpo da requisição
    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserCreateRequest request) {
        UserDto user = userService.addUser(request);
        URI location = URI.create("/user/" + user.id());

        return ResponseEntity.created(location).body(user);//Retorna o novo usuário e o location do recurso.
    }

    //Endpoint para alterar um usuário criado anteriormente. Recebe o ID do usuário pela URL e os dados para alterar no corpo da requisição.
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserUpdateRequest request) {
        UserDto updatedUser = userService.updateUser(id, request);

        return ResponseEntity.ok(updatedUser);
    }

    //Endpoint para deletar um usuário. Recebe o ID do post pela URL.
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

}
