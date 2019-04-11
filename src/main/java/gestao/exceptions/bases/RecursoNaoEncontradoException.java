package gestao.exceptions.bases;

public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException() {
    }

    public RecursoNaoEncontradoException(String message) {
        super(message);
    }
}
