package gestao.exceptions.produto;

import gestao.exceptions.bases.RecursoNaoEncontradoException;

public class ProdutoNaoEncontradoException extends RecursoNaoEncontradoException {
    public ProdutoNaoEncontradoException() {
        super("produto não encontrado");
    }
}