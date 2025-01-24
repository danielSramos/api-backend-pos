package br.com.unifap.pos.atv_sinc1.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    //Exception personalizada para uso na verificação do usuário com email já cadastrado.
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
