package gestao.config;

import gestao.models.produto.Produto;
import gestao.models.hospital.Endereco;
import gestao.models.banco_de_sangue.BancoDeSangueENUM;
import gestao.models.hospital.Hospital;
import gestao.models.hospital.HospitalDTO;
import gestao.models.leito.TipoLeitoENUM;
import gestao.models.paciente.Paciente;
import gestao.models.paciente.SexoPacienteENUM;
import gestao.repositories.hospital.HospitalRepository;
import gestao.services.HospitalService;
import gestao.services.PacienteService;
import gestao.services.ProdutoService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.*;

import static gestao.models.leito.TipoLeitoENUM.*;

@Component
public class Migracao2 implements InitializingBean {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    ProdutoService produtoService;

    @Override
    public void afterPropertiesSet() throws Exception {
        criaHospitais();
        criaPacientes();
        inicializaBancoDeSangue();
        inicializaProdutos();
    }

    private void criaHospitais() {
        String[] nomeHospital = {"Hospital Getúlio Vargas Filho", "Fundação Municipal de Saúde", "Instituto Nacional do Câncer ", "Hospital Sao Paulo", "Hospital de Caridade Dr. Astrogildo de Azevedo", "Hospital Amazônia"};
        List<Endereco> enderecos = endereco();
        for (int i = 0; i < 6; i++) {
            HospitalDTO hospitalDTO = new HospitalDTO();
            hospitalDTO.setNome(nomeHospital[i]);
            hospitalDTO.setEndereco(enderecos.get(i));
            hospitalDTO.setLeitos(mapLeitos());

            hospitalService.create(hospitalDTO);
        }
    }

    public List<Endereco> endereco() {
        String[] endereco1 = {"Fonseca", "Niteroi", "Rua Teixeira de Freitas", "RJ", "30"};
        String[] endereco2 = {"Centro", "Niteroi", "Rua Visconde de Sepetiba", "RJ", "987"};
        String[] endereco3 = {"Porto Novo", "São Gonçalo", "Rua Dr. Francisco Portela", "RJ", "2421"};
        String[] endereco4 = {"Vila Clementino", "São Paulo", "R. Napoleão de Barros", "SP", "715"};
        String[] endereco5 = {"Passo D’reia", "Santa Maria", "Rua Ernesto Alves", "RS", "255"};
        String[] endereco6 = {"São Brás", "Belém", "Tv. Nove de Janeiro", "PA", "1267"};
        List<String[]> listaEndereco = new ArrayList<>();
        listaEndereco.add(endereco1);
        listaEndereco.add(endereco2);
        listaEndereco.add(endereco3);
        listaEndereco.add(endereco4);
        listaEndereco.add(endereco5);
        listaEndereco.add(endereco6);

        List<Endereco> enderecos = new ArrayList<Endereco>();
        for (int i = 0; i < 6; i++) {
            String[] temp = listaEndereco.get(i);
            Endereco endereco = new Endereco();
            endereco.setBairro(temp[0]);
            endereco.setLocalidade(temp[1]);
            endereco.setLogradouro(temp[2]);
            endereco.setUf(temp[3]);
            endereco.setNumero(temp[4]);
            enderecos.add(endereco);
        }
        return enderecos;
    }

    public Map<TipoLeitoENUM, Integer> mapLeitos() {
        Map<TipoLeitoENUM, Integer> map = new HashMap<>();
        map.put(CIR_BUCOMAXILOFACIAL, 10);
        map.put(CIR_GINECOLOGIA, 10);
        map.put(CIR_ENDOCRINOLOGIA, 15);
        return map;
    }


    private void criaPacientes() {
        BindingResult result = mock(BindingResult.class);
        Paciente paciente = new Paciente();
        paciente.setNome("Paciente 1");
        paciente.setDataNascimento(LocalDate.parse("2000-01-01"));
        paciente.setCpf("141.537.357-44");
        paciente.setSexo(SexoPacienteENUM.M);
        pacienteService.salvaPaciente(paciente, result);

        Paciente paciente2 = new Paciente();
        paciente2.setNome("Paciente 2");
        paciente2.setDataNascimento(LocalDate.parse("2000-01-01"));
        paciente2.setCpf("108.230.707-60");
        paciente2.setSexo(SexoPacienteENUM.M);
        pacienteService.salvaPaciente(paciente2, result);

        Paciente paciente3 = new Paciente();
        paciente3.setNome("Paciente 3");
        paciente3.setDataNascimento(LocalDate.parse("2000-01-01"));
        paciente3.setCpf("099.314.407-16");
        paciente3.setSexo(SexoPacienteENUM.F);
        pacienteService.salvaPaciente(paciente3, result);
    }

    private void inicializaBancoDeSangue() {
        List<Hospital> hospitais = hospitalRepository.findAll();
        for (Hospital h : hospitais) {
            h.setBancoDeSangue(createDefault());
            hospitalRepository.save(h);
        }
    }

    public Map<BancoDeSangueENUM, Integer> createDefault() {
        Map<BancoDeSangueENUM, Integer> bancoDeSangue = new HashMap<>();
        Arrays.stream(BancoDeSangueENUM.values()).forEach(x -> bancoDeSangue.put(x, 10));
        return bancoDeSangue;
    }

    private void inicializaProdutos() {
        List<Hospital> hospital = hospitalRepository.findAll();
        BindingResult result = mock(BindingResult.class);

        for (Hospital h : hospital) {
            Produto produto1 = new Produto();
            produto1.setNome("Luva hospitalar");
            produto1.setQuantidade(10);

            Produto produto2 = new Produto();
            produto2.setNome("Estetoscópio");
            produto2.setQuantidade(10);

            produtoService.adicionar(produto1, h.getId(), result);
            produtoService.adicionar(produto2, h.getId(), result);
        }
    }
}
