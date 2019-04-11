package gestao.exceptions.Produto;


import gestao.exceptions.bases.RecursoNaoEncontradoException;

public class ProdutoNaoEncontradoException extends RecursoNaoEncontradoException {

    public ProdutoNaoEncontradoException() {
        super("Produto não encontrado");
    }
}