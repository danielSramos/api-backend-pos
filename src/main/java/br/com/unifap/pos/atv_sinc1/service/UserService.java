package br.com.unifap.pos.atv_sinc1.service;

import br.com.unifap.pos.atv_sinc1.dto.PostDto;
import br.com.unifap.pos.atv_sinc1.dto.UserCreateRequest;
import br.com.unifap.pos.atv_sinc1.dto.UserDto;
import br.com.unifap.pos.atv_sinc1.dto.UserUpdateRequest;
import br.com.unifap.pos.atv_sinc1.dto.UserWithPostDto;
import br.com.unifap.pos.atv_sinc1.exception.EmailAlreadyExistsException;
import br.com.unifap.pos.atv_sinc1.exception.ResourceNotFoundException;
import br.com.unifap.pos.atv_sinc1.model.User;
import br.com.unifap.pos.atv_sinc1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

    //Injeção de dependências
    private final UserRepository repository;
    private final UserRepository userRepository;

    public UserService(UserRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    //serviço para busca de usuário pelo ID.
    public UserDto getUserById(Long id) {
        //Busca o usuário no banco de dados e retorna uma exception caso o usuário não exista.
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        //Monta o DTO de resposta e retorna para o controller
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt().toString()
        );
    }

    //Serviço para listagem de todos os usuários com seus posts.
    public List<UserWithPostDto> getAllUsersWithPosts() {
        /**
         * Chama o serviço para listagem de todos os usuários com posts e monta uma lista para retorno.
         * Esse método de mapeamento é uma forma mais limpa de manipulação de coleções e listas utilizadas no Java.
         * No final, monta o DTO de resposta e retorna todos os usuários encontrados com seus posts em formato de lista.
         */
        return repository.findAll().stream()
                .map(user -> new UserWithPostDto(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getCreatedAt(),
                        user.getPosts().stream()
                                .map(post -> new PostDto(
                                        post.getId(),
                                        post.getTitle(),
                                        post.getContent(),
                                        post.getCreatedAt().toString()
                                )).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    //Serviço para criação de um novo usuário.
    public UserDto addUser(UserCreateRequest request) {
        //Verifica se o email já está cadastrado, caso esteja, retorna uma mensagem e lança uma exception.
        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("Email already exists: " + request.email());
        }

        User user = new User();

        user.setName(request.name());
        user.setEmail(request.email());
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = repository.save(user);

        return new UserDto(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt().toString()
        );
    }

    //Serviço para atualização de um usuário baseado no ID. Recebe o ID pela URl.
    public UserDto updateUser(Long id, UserUpdateRequest request) {
        //Verifica se o usuário existe, caso não exista, retorna uma mensagem e lança uma exception.
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        //Verifica se o atributo da request veio null ou não foi modificado, caso tenha sido modificado, salva no novo objeto.
        if (!Objects.equals(user.getName(), request.name()) && !Objects.isNull(request.name())) {
            user.setName(request.name());
        }
        //Verifica se o atributo da request veio null ou não foi modificado, caso tenha sido modificado, salva no novo objeto.
        if (!Objects.equals(user.getEmail(), request.email()) && !Objects.isNull(request.email())) {
            user.setEmail(request.email());
        }

        User updatedUser = repository.save(user);

        return new UserDto(
                updatedUser.getId(),
                updatedUser.getName(),
                updatedUser.getEmail(),
                updatedUser.getCreatedAt().toString()
        );
    }

    //Serviço para deletar um usuário baseado no ID. Não tem retorno. Recebe o ID pela URL.
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

}
