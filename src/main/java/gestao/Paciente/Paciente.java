package gestao.Paciente;


import gestao.HistoricoPaciente.HistoricoPaciente;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name= "paciente")
public class Paciente {



    @Id
    private Long cpf;
    private String nome;
    private LocalDate dataNascimento;
    private SexoPacienteENUM sexo;
    private LocalDateTime ultimoCheckin;
    private boolean emAtendimento;

    private Long hospital;

    @OneToMany(fetch = FetchType.LAZY)
    private List<HistoricoPaciente> historicoPaciente;



    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
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

    public Long getHospital() {
        return hospital;
    }

    public void setHospital(Long hospital) {
        this.hospital = hospital;
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
