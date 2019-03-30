package gestao.HistoricoPaciente;


import gestao.Paciente.Paciente;
import gestao.Paciente.PacienteRepository;
import gestao.Paciente.PacienteSemCheckoutException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoricoPacienteService {

  //  @Autowired
    private PacienteRepository pacienteRepository;

   // @Autowired
    private HistoricoPacienteRepository historicoPacienteRepository;

    public HistoricoPacienteService(HistoricoPacienteRepository historicoPacienteRepository, PacienteRepository pacienteRepository){
        this.historicoPacienteRepository = historicoPacienteRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public void checkin(long cpf, HistoricoPaciente historico) throws PacienteSemCheckoutException {
        Paciente paciente = pacienteRepository.findByCpf(cpf);
        if (paciente.isEmAtendimento()) throw new PacienteSemCheckoutException("Paciente já está em atendimento");
        paciente.setEmAtendimento(true);
        historico.setPaciente(paciente);
        historico.setHospital(paciente.getHospital());
        historico.setDataEntradaHospital(LocalDateTime.now());
        paciente.setUltimoCheckin(historico.getDataEntradaHospital());
        List<HistoricoPaciente> listaHistorico= paciente.pegaHistoricoPaciente();
        listaHistorico.add(historico);
        paciente.setHistoricoPaciente(listaHistorico);
        historicoPacienteRepository.saveAndFlush(historico);
        pacienteRepository.saveAndFlush(paciente);

    }

    public void internado(long cpf, HistoricoPaciente historico) {
        Paciente paciente = pacienteRepository.findByCpf(cpf);
        HistoricoPaciente historicoPaciente = historicoPacienteRepository.findByDataEntradaHospital(paciente.getUltimoCheckin());
        historicoPaciente.setLeito(historico.getLeito());
        historicoPacienteRepository.saveAndFlush(historicoPaciente);
    }

    public void checkout(long cpf, HistoricoPaciente historico) throws PacienteSemCheckoutException {
        Paciente paciente = pacienteRepository.findByCpf(cpf);
        if (!paciente.isEmAtendimento()) throw new PacienteSemCheckoutException("Paciente não deu entrada no hospital");
        paciente.setEmAtendimento(false);
        historico.setPaciente(paciente);
        HistoricoPaciente historicoPaciente = historicoPacienteRepository.findByDataEntradaHospital(paciente.getUltimoCheckin());
        historicoPaciente.setDataSaidaHospital(LocalDateTime.now());
        pacienteRepository.saveAndFlush(paciente);
        historicoPacienteRepository.saveAndFlush(historicoPaciente);
    }



}
