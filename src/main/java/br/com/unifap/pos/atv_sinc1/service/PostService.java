package br.com.unifap.pos.atv_sinc1.service;

import br.com.unifap.pos.atv_sinc1.dto.PostCreateRequest;
import br.com.unifap.pos.atv_sinc1.dto.PostDto;
import br.com.unifap.pos.atv_sinc1.dto.PostUpdateRequest;
import br.com.unifap.pos.atv_sinc1.exception.ResourceNotFoundException;
import br.com.unifap.pos.atv_sinc1.model.Post;
import br.com.unifap.pos.atv_sinc1.model.User;
import br.com.unifap.pos.atv_sinc1.repository.PostRepository;
import br.com.unifap.pos.atv_sinc1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PostService {

    //Injeção de dependências
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository PostRepository, UserRepository userRepository) {
        this.postRepository = PostRepository;
        this.userRepository = userRepository;
    }

    //serviço para busca de usuário pelo ID.
    public PostDto getPostById(Long id) {
        //Busca o post no banco de dados e retorna uma exception caso o post não exista.
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));

        //Monta o DTO de resposta e retorna para o controller
        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt().toString()
        );
    }

    //Serviço para listagem de todos os posts.
    public List<PostDto> getAllPosts() {
        /**
         * Chama o serviço para listagem de todos os posts e monta uma lista para retorno.
         * Esse método de mapeamento é uma forma mais limpa de manipulação de coleções e listas utilizadas no Java.
         * No final, monta o DTO de resposta e retorna todos os posts encontrados em formato de lista.
         */
        return postRepository.findAll().stream()
                .map(post -> new PostDto(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getCreatedAt().toString()
                )).collect(Collectors.toList());
    }

    //Serviço para criação de um novo post.
    public PostDto addPost(PostCreateRequest request) {
        //Verifica se o usuário já está cadastrado, caso não esteja, retorna uma mensagem e lança uma exception.
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.userId()));

        Post post = new Post();
        post.setTitle(request.title());
        post.setContent(request.content());
        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());

        Post savedPost = postRepository.save(post);

        return new PostDto(
                savedPost.getId(),
                savedPost.getTitle(),
                savedPost.getContent(),
                savedPost.getCreatedAt().toString()
        );
    }
    //Serviço para atualização de um post baseado no ID. Recebe o ID pela URl.
    public PostDto updatePost(Long id, PostUpdateRequest request) {
        //Verifica se o post existe, caso não exista, retorna uma mensagem e lança uma exception.
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));

        //Verifica se o atributo da request veio null ou não foi modificado, caso tenha sido modificado, salva no novo objeto.
        if (!Objects.equals(post.getTitle(), request.title()) && !Objects.isNull(request.title())) {
            post.setTitle(request.title());
        }
        //Verifica se o atributo da request veio null ou não foi modificado, caso tenha sido modificado, salva no novo objeto.
        if (!Objects.equals(post.getContent(), request.content()) && !Objects.isNull(request.content())) {
            post.setContent(request.content());
        }

        Post savedPost = postRepository.save(post);

        return new PostDto(
                savedPost.getId(),
                savedPost.getTitle(),
                savedPost.getContent(),
                savedPost.getCreatedAt().toString()
        );
    }

    //Serviço para deletar um usuário baseado no ID. Não tem retorno. Recebe o ID pela URL.
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

}
