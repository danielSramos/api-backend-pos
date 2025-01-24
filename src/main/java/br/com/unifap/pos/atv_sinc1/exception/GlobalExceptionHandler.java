package br.com.unifap.pos.atv_sinc1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    /*
    * Controlador global para captura de exceções lançadas pela api.
    *
    * */

    @ExceptionHandler(EmailAlreadyExistsException.class)//Captura as exceptions do tipo EmailAlreadyExistsException e retorna uma resposta genéria para o usuário.
    public ResponseEntity<String> emailAlreadyExistsException(final EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)//Captura as exceptions do tipo ResourceNotFoundException e retorna uma resposta genéria para o usuário.
    public ResponseEntity<String> resourceNotFoundException(final ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)//Captura as exceptions do tipo Exception e retorna uma resposta genéria para o usuário. Esse método captura qualquer tipo de execção que for lançada evitando retornar erros para o cliente.
    public ResponseEntity<String> exception(final Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
}
