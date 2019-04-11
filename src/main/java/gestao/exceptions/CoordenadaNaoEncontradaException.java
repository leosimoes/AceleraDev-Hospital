package gestao.exceptions;

import gestao.exceptions.bases.RecursoNaoEncontradoException;

public class CoordenadaNaoEncontradaException extends RecursoNaoEncontradoException {
    public CoordenadaNaoEncontradaException() {
        super("Coordenada não encontrada para o endereço informado!");
    }

    public CoordenadaNaoEncontradaException(String message) {
        super(message);
    }
}
