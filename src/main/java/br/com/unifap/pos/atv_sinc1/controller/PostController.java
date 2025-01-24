package br.com.unifap.pos.atv_sinc1.controller;

import br.com.unifap.pos.atv_sinc1.dto.PostCreateRequest;
import br.com.unifap.pos.atv_sinc1.dto.PostDto;
import br.com.unifap.pos.atv_sinc1.dto.PostUpdateRequest;
import br.com.unifap.pos.atv_sinc1.service.PostService;
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
@RequestMapping("/posts")
public class PostController {

    //Injeção de dependências para que o Spring gerencie a disponibilidade da classe service.
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //Endpoint para listagem de todos os posts.
    @GetMapping
    public ResponseEntity<List<PostDto>> getPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    //Endpoint para listagem de post pelo ID. Recebe o ID pela URL.
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //Endpoint para criar um novo post. Recebe os dados no corpo da requisição
    @PostMapping
    public ResponseEntity<PostDto> addPost(@RequestBody PostCreateRequest request) {
        PostDto post = postService.addPost(request);
        URI location = URI.create("/post/" + post.getId());

        return ResponseEntity.created(location).body(post);//Retorna o novo post e o location do recurso.
    }

    //Endpoint para alterar um post criado anteriormente. Recebe o ID do post pela URL e os dados para alterar no corpo da requisição.
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable Long id,
            @RequestBody PostUpdateRequest request) {
        PostDto updatedPost = postService.updatePost(id, request);

        return ResponseEntity.ok(updatedPost);
    }

    //Endpoint para deletar um post. Recebe o ID do post pela URL.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);

        return ResponseEntity.noContent().build();
    }

}
