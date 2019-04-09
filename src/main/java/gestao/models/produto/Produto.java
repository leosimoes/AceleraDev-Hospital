package gestao.models.produto;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
public class Produto {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long Id;
    @NotEmpty
    private String nome;
    @NotEmpty
    private String descricao;
    @Min(1)
    private Integer quantidade;

    public Long getId() {
        return Id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descrição) {
        this.descricao = descrição;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void incrementaQuantidade(Integer quantidade) {
        this.quantidade += quantidade;
    }
    
    public void decrementaQuantidade(Integer quantidade) {
        this.quantidade += quantidade;
    }
    
    @Override
    public String toString() {
        return "Produto{" + "Id=" + Id + ", nome=" + nome + ", descricao=" + descricao + ", quantidade=" + quantidade + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }
 
}
