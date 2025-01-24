package br.com.unifap.pos.atv_sinc1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    //Exception personalizada para uso na verificação do recurso. Caso o recurso buscado não exista retorna uma mensagem informando ao usuário.
    public ResourceNotFoundException(String message) {
        super(message);
    }

}
