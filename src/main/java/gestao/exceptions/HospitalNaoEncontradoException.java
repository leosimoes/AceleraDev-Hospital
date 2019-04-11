package gestao.exceptions;

import gestao.exceptions.bases.RecursoNaoEncontradoException;

public class HospitalNaoEncontradoException extends RecursoNaoEncontradoException {
    public HospitalNaoEncontradoException(String message) {
        super(message);
    }

    public HospitalNaoEncontradoException() {
        super("Hospital não encontrado");
    }
}
