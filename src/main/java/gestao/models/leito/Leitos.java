package gestao.models.leito;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public class Leitos {

    @JsonEnumDefaultValue
    private TipoLeitoENUM tipo;

    private Integer quantidade;

    public TipoLeitoENUM getTipo() {
        return tipo;
    }

    public void setTipo(TipoLeitoENUM tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
