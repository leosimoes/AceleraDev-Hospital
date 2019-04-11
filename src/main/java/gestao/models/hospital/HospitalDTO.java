package gestao.models.hospital;

import gestao.models.Endereco;
import gestao.models.leito.TipoLeitoENUM;

import javax.persistence.ElementCollection;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class HospitalDTO {
    @NotBlank(message = "O hospital tem que ter nome.")
    private String nome;

    @NotNull(message = "O hospital deve ter um endereço.")
    @Valid
    private Endereco endereco;

    @NotNull(message = "O hospital deve ter um Leitos.")
    @Valid
    @ElementCollection
    private Map<TipoLeitoENUM, Integer> leitos;

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Map<TipoLeitoENUM, Integer> getLeitos() {
        return leitos;
    }

}
