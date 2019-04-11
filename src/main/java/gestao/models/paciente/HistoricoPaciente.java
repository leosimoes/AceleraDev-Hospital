package gestao.models.paciente;

import gestao.models.hospital.Hospital;
import gestao.models.leito.TipoLeitoENUM;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "historico_paciente")
public class HistoricoPaciente {

    @Id
    private LocalDateTime dataEntradaHospital;
    private LocalDateTime dataSaidaHospital;
    @OneToOne
    private Hospital hospital;
    @ManyToOne
    private Paciente paciente;
    private TipoLeitoENUM leito;
    private String descricaoAtendimento;



    public LocalDateTime getDataEntradaHospital() {
        return dataEntradaHospital;
    }

    public void setDataEntradaHospital(LocalDateTime dataEntradaHospital) {
        this.dataEntradaHospital = dataEntradaHospital;
    }

    public LocalDateTime getDataSaidaHospital() {
        return dataSaidaHospital;
    }

    public void setDataSaidaHospital(LocalDateTime dataSaidaHospital) {
        this.dataSaidaHospital = dataSaidaHospital;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public TipoLeitoENUM getLeito() {
        return leito;
    }

    public void setLeito(TipoLeitoENUM leito) {
        this.leito = leito;
    }

    public String getDescricaoAtendimento() {
        return descricaoAtendimento;
    }

    public void setDescricaoAtendimento(String descricaoAtendimento) {
        this.descricaoAtendimento = descricaoAtendimento;
    }
}
