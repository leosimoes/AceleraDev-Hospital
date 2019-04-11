package gestao.exceptions.paciente;

import gestao.exceptions.bases.RecursoNaoEncontradoException;

public class PacienteNaoEncontradoException extends RecursoNaoEncontradoException {

    public PacienteNaoEncontradoException(String msg) {
        super(msg);
    }
}
