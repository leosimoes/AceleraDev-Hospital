package gestao.services;


import gestao.exceptions.HospitalNaoEncontradoException;
import gestao.models.hospital.Endereco;
import gestao.models.hospital.Hospital;
import gestao.models.hospital.HospitalDTO;
import gestao.repositories.hospital.HospitalRepository;
import gestao.utils.geolocalizacao.Coordenadas;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class HospitalServiceTest {

    @Mock
    private HospitalRepository hospitalRepository;

    @InjectMocks
    private HospitalService hospitalService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void deveRetornarPaginaDeHospitais() {
        Page<Hospital> listaHospital = Page.empty();

        listaHospital.getTotalPages();

        Pageable page = Mockito.mock(Pageable.class);

        when(this.hospitalRepository.findAll(page)).thenReturn(listaHospital);

        Page<Hospital> hospitais = this.hospitalService.findAll(page);

        Assert.assertEquals(hospitais, listaHospital);
        Assert.assertEquals(hospitais.getTotalElements(),listaHospital.getTotalElements());
    }

//
    @Test
    void deveCriarUmHospitalViaDTO() {
        Hospital hospital = Mockito.mock(Hospital.class);
        HospitalDTO hospitalDto = Mockito.mock(HospitalDTO.class);
//        when(Hospital.criarComDTO(hospitalDto)).thenReturn(hospital);
        when(this.hospitalRepository.save(any(Hospital.class))).thenReturn(hospital);

        Assert.assertEquals(hospital, this.hospitalService.create(hospitalDto));
    }
//
    @Test
    void deveBuscarUmHospitalPeloId() {
        Hospital hospital = Mockito.mock(Hospital.class);
        when(this.hospitalRepository.findById(anyLong())).thenReturn(Optional.of(hospital));
        Assert.assertEquals(this.hospitalService.find(anyLong()), hospital);
    }


    @Test
    void DeveFazerOUpdateDoHospitalSemException() {
        Hospital hospital = Mockito.mock(Hospital.class);
        HospitalDTO dto = Mockito.mock(HospitalDTO.class);
        when(this.hospitalRepository.findById(anyLong())).thenReturn(Optional.of(hospital));
        when(this.hospitalRepository.save(hospital)).thenReturn(hospital);
        this.hospitalService.update(anyLong(), dto);
    }
//
    @Test
    void deveRetornarExceptionCasoHospitalNaoExista() {
        when(this.hospitalRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(HospitalNaoEncontradoException.class, () -> {
            this.hospitalService.update(anyLong(), Mockito.mock(HospitalDTO.class));
        });
    }


    @Test
    void deveRetornaExceptionCasoNaoExistaHospitalAoDeletar() {
        when(this.hospitalRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(HospitalNaoEncontradoException.class, () -> {
            this.hospitalService.delete(anyLong());
        });
    }
    @Test
    void deveRetornarListaDeHospitaisProximosEmOrdemDeProximidade(Coordenadas geocolocalizacao) {

        List<Hospital> hospitais = new  ArrayList<>();
        List<Coordenadas> coordenadas = new ArrayList<>();
        coordenadas.add(new Coordenadas(-43.0789753,-22.8814112));
        coordenadas.add(new Coordenadas(-43.118191,-22.8929175));
        coordenadas.add(new Coordenadas(-53.8324964,-29.6841972));

        final String  nome = "nome_";

        coordenadas.forEach(x -> {
            int contador = 1;
            Endereco endereco = Mockito.mock(Endereco.class);
            Hospital hospital = new Hospital();
            hospital.setEndereco(new Endereco());
            endereco.setCoordenadas(x);
            hospital.setEndereco(endereco);
            hospital.setNome(nome + contador);
            hospitais.add(hospital);
            contador++;
            System.out.println(hospital);
        });

        List<Hospital> results = Arrays.asList(
                hospitais.get(1),
                hospitais.get(0),
                hospitais.get(2)
        );

        when(this.hospitalRepository.findAll()).thenReturn(hospitais);

        assertEquals(results, this.hospitalService.procurarPorHospitaisProximos(new Coordenadas(-43.118190,-22.8929171)));

    }

}