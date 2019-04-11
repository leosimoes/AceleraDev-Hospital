package gestao.services;

import gestao.models.banco_de_sangue.BancoDeSangueENUM;
import gestao.models.hospital.Hospital;
import gestao.repositories.hospital.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classe responsável pela implementação dos serviços relacionados ao banco de sangue.
 *
 * @author Jardel Casteluber
 *
 */
@Service
public class BancoDeSangueService {
    @Autowired
    HospitalRepository hospitalRepository;



    public boolean adicionarSangue(long id, BancoDeSangueENUM tipo, Integer quantidadeEmLitros) {
        try {
            Hospital hospital = hospitalRepository.findById(id).get();
            Integer add = hospital.getBancoDeSangue().get(tipo);
            add += quantidadeEmLitros;
            Map<BancoDeSangueENUM, Integer> bancoDeSangue = hospital.getBancoDeSangue();
            bancoDeSangue.put(tipo, add);
            hospital.setBancoDeSangue(bancoDeSangue);
            hospitalRepository.save(hospital);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean removerSangue(long cpf, BancoDeSangueENUM tipo, Integer quantidadeEmLitros) {
       try {
           Hospital hospital = hospitalRepository.findById(cpf).get();
           Integer sub = hospital.getBancoDeSangue().get(tipo);
           sub -= quantidadeEmLitros;
           if (sub < 0) sub = 0; //Restrição de não-negatividade
           Map<BancoDeSangueENUM, Integer> bancoDeSangue = hospital.getBancoDeSangue();
           bancoDeSangue.put(tipo, sub);
           hospital.setBancoDeSangue(bancoDeSangue);
           hospitalRepository.save(hospital);
           return true;
       } catch (Exception ex) {
           return false;
       }
    }


    public List<BancoDeSangueENUM> compatibilidadeSanguinea(BancoDeSangueENUM tipo) {

        List<BancoDeSangueENUM> sanguesCompativeis = new ArrayList<>();

        if (tipo.equals(BancoDeSangueENUM.A_P)) {
            sanguesCompativeis.add(BancoDeSangueENUM.A_P);
            sanguesCompativeis.add(BancoDeSangueENUM.A_N);
            sanguesCompativeis.add(BancoDeSangueENUM.O_P);
            sanguesCompativeis.add(BancoDeSangueENUM.O_N);
            return sanguesCompativeis;
        }

        if (tipo.equals(BancoDeSangueENUM.A_N)) {
            sanguesCompativeis.add(BancoDeSangueENUM.A_N);
            sanguesCompativeis.add(BancoDeSangueENUM.O_N);
            return sanguesCompativeis;
        }

        if (tipo.equals(BancoDeSangueENUM.B_P)) {
            sanguesCompativeis.add(BancoDeSangueENUM.B_P);
            sanguesCompativeis.add(BancoDeSangueENUM.B_N);
            sanguesCompativeis.add(BancoDeSangueENUM.O_P);
            sanguesCompativeis.add(BancoDeSangueENUM.O_N);
            return sanguesCompativeis;
        }

        if (tipo.equals(BancoDeSangueENUM.B_N)) {
            sanguesCompativeis.add(BancoDeSangueENUM.B_N);
            sanguesCompativeis.add(BancoDeSangueENUM.O_N);
            return sanguesCompativeis;
        }

        if (tipo.equals(BancoDeSangueENUM.AB_P)) {
            sanguesCompativeis.add(BancoDeSangueENUM.A_P);
            sanguesCompativeis.add(BancoDeSangueENUM.A_N);
            sanguesCompativeis.add(BancoDeSangueENUM.B_P);
            sanguesCompativeis.add(BancoDeSangueENUM.B_N);
            sanguesCompativeis.add(BancoDeSangueENUM.AB_P);
            sanguesCompativeis.add(BancoDeSangueENUM.AB_N);
            sanguesCompativeis.add(BancoDeSangueENUM.O_P);
            sanguesCompativeis.add(BancoDeSangueENUM.O_N);
            return sanguesCompativeis;
        }

        if (tipo.equals(BancoDeSangueENUM.AB_N)) {
            sanguesCompativeis.add(BancoDeSangueENUM.A_N);
            sanguesCompativeis.add(BancoDeSangueENUM.B_N);
            sanguesCompativeis.add(BancoDeSangueENUM.AB_N);
            sanguesCompativeis.add(BancoDeSangueENUM.O_N);
            return sanguesCompativeis;
        }

        if (tipo.equals(BancoDeSangueENUM.O_P)) {
            sanguesCompativeis.add(BancoDeSangueENUM.O_P);
            sanguesCompativeis.add(BancoDeSangueENUM.O_N);
            return sanguesCompativeis;
        }

        if (tipo.equals(BancoDeSangueENUM.O_N)) {
            sanguesCompativeis.add(BancoDeSangueENUM.O_N);
            return sanguesCompativeis;
        }
        return null;
    }

}
