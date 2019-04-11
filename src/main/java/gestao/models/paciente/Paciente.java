package gestao.models.paciente;


import gestao.models.hospital.Hospital;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name= "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @CPF
    //@UniqueElements
    @NotBlank
    private String cpf;
    @NotBlank
    private String nome;
    @NotNull
    private LocalDate dataNascimento;
    @NotNull
    private SexoPacienteENUM sexo;
    private LocalDateTime ultimoCheckin;
    private boolean emAtendimento;

    @OneToMany(fetch = FetchType.LAZY)
    private List<HistoricoPaciente> historicoPaciente;


    public long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public SexoPacienteENUM getSexo() {
        return sexo;
    }

    public void setSexo(SexoPacienteENUM sexo) {
        this.sexo = sexo;
    }

    public LocalDateTime getUltimoCheckin() {
        return ultimoCheckin;
    }

    public void setUltimoCheckin(LocalDateTime ultimoCheckin) {
        this.ultimoCheckin = ultimoCheckin;
    }

    public boolean isEmAtendimento() {
        return emAtendimento;
    }

    public void setEmAtendimento(boolean emAtendimento) {
        this.emAtendimento = emAtendimento;
    }

    public List<HistoricoPaciente> getHistoricoPaciente() {
        return null;
    }

    public List<HistoricoPaciente> pegaHistoricoPaciente() {
        return historicoPaciente;
    }

    public void setHistoricoPaciente(List<HistoricoPaciente> historicoPaciente) {
        this.historicoPaciente = historicoPaciente;
    }
}
