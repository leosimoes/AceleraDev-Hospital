package gestao.services;


import gestao.exceptions.LeitoIndisponivelException;
import gestao.exceptions.paciente.PacienteSemCheckinException;
import gestao.exceptions.paciente.PacienteSemCheckoutException;
import gestao.models.hospital.Hospital;
import gestao.models.leito.Leitos;
import gestao.models.leito.TipoLeitoENUM;
import gestao.models.paciente.HistoricoPaciente;
import gestao.models.paciente.Paciente;
import gestao.repositories.HistoricoPacienteRepository;
import gestao.repositories.PacienteRepository;
import gestao.repositories.hospital.HospitalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Classe responsável pela implementação dos serviços relacionados ao historico do paciente.
 *
 * @author Jardel Casteluber
 *
 */

@Service
public class HistoricoPacienteService {

    //  @Autowired
    private PacienteRepository pacienteRepository;

    // @Autowired
    private HistoricoPacienteRepository historicoPacienteRepository;

    private HospitalRepository hospitalRepository;

    public HistoricoPacienteService(HistoricoPacienteRepository historicoPacienteRepository, PacienteRepository pacienteRepository, HospitalRepository hospitalRepository) {
        this.historicoPacienteRepository = historicoPacienteRepository;
        this.pacienteRepository = pacienteRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public boolean checkin(String cpf, Hospital hospital) {
        cpf = cpf.replaceAll(Pattern.quote("."), "").replaceAll(("-"), "");
        try {
            Paciente paciente = pacienteRepository.findByCpf(cpf);
            if (paciente.isEmAtendimento()) throw new PacienteSemCheckoutException();
            paciente.setEmAtendimento(true);
            HistoricoPaciente historicoPaciente = new HistoricoPaciente();
            historicoPaciente.setPaciente(paciente);
            historicoPaciente.setHospital(hospitalRepository.findById(hospital.getId()).get());
            historicoPaciente.setDataEntradaHospital(LocalDateTime.now());
            paciente.setUltimoCheckin(historicoPaciente.getDataEntradaHospital());
            List<HistoricoPaciente> listaHistorico = paciente.pegaHistoricoPaciente();
            listaHistorico.add(historicoPaciente);
            paciente.setHistoricoPaciente(listaHistorico);
            historicoPacienteRepository.saveAndFlush(historicoPaciente);
            pacienteRepository.saveAndFlush(paciente);
            return true;
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public boolean internar(String cpf, Leitos leito) {
        cpf = cpf.replaceAll(Pattern.quote("."), "").replaceAll(("-"), "");
        try {

            Paciente paciente = pacienteRepository.findByCpf(cpf);
            HistoricoPaciente historicoPaciente = historicoPacienteRepository.findByDataEntradaHospital(paciente.getUltimoCheckin());
            if (historicoPaciente.getLeito() != null) {  //Libera leito atual para receber outro leito
                liberarLeito(historicoPaciente.getHospital(), historicoPaciente);
            }
            Map<TipoLeitoENUM, Integer> leitos = historicoPaciente.getHospital().getLeitos();
            if (leitos.get(leito.getTipo()) > 0) {
                leitos.put(leito.getTipo(), (leitos.get(leito.getTipo()) - 1));
                Hospital hospital = historicoPaciente.getHospital();
                hospital.setLeitos(leitos);
                hospitalRepository.saveAndFlush(hospital);
                historicoPaciente.setLeito(leito.getTipo());
                historicoPacienteRepository.saveAndFlush(historicoPaciente);
                return true;
            } else {
                throw new LeitoIndisponivelException();
            }

        } catch (NullPointerException ex) {
            return false;
        }
    }

    public boolean checkout(String cpf, String descricaoAtendimento) {
        cpf = cpf.replaceAll(Pattern.quote("."), "").replaceAll(("-"), "");
        try {
            Paciente paciente = pacienteRepository.findByCpf(cpf);
            if (!paciente.isEmAtendimento())
                throw new PacienteSemCheckinException();
            paciente.setEmAtendimento(false);
            HistoricoPaciente historicoPaciente = historicoPacienteRepository.findByDataEntradaHospital(paciente.getUltimoCheckin());
            historicoPaciente.setDescricaoAtendimento(descricaoAtendimento);
            historicoPaciente.setDataSaidaHospital(LocalDateTime.now());
            pacienteRepository.saveAndFlush(paciente);
            historicoPacienteRepository.saveAndFlush(historicoPaciente);
            liberarLeito(historicoPaciente.getHospital(), historicoPaciente);
            return true;
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public void liberarLeito(Hospital hospital, HistoricoPaciente historicoPaciente) {
        Map<TipoLeitoENUM, Integer> leitos = historicoPaciente.getHospital().getLeitos();
        leitos.put(historicoPaciente.getLeito(), leitos.get(historicoPaciente.getLeito()) + 1);
        hospital.setLeitos(leitos);
        hospitalRepository.saveAndFlush(hospital);
    }


}
