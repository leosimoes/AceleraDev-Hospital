package gestao.Hospital;

import gestao.repositories.hospital.HospitalRepository;
import gestao.services.HospitalService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;

class HospitalServiceTest {

    @Mock
    private HospitalRepository hospitalRepository;

    @InjectMocks
    private HospitalService hospitalService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


//    @Test
//    void deveRetornarTodosOsHospitais() {
//        List<Hospital> listaHospital = new ArrayList<>();
//        listaHospital.add(Mockito.mock(Hospital.class));
//        when(this.hospitalRepository.findAll()).thenReturn(listaHospital);
//
//        List<Hospital> hospitais = this.hospitalService.findAll();
//
//        Assert.assertEquals(hospitais, listaHospital);
//        Assert.assertEquals(hospitais.size(), listaHospital.size());
//    }
//
//    @Test
//    void deveCriarUmHospital() {
//        Hospital hospital = Mockito.mock(Hospital.class);
//        when(this.hospitalRepository.save(any(Hospital.class))).thenReturn(hospital);
//        Assert.assertEquals(hospital, this.hospitalService.create(hospital));
//
//    }
//
//    @Test
//    void deveBuscarUmHospitalPeloId() {
//        Hospital hospital = Mockito.mock(Hospital.class);
//        when(this.hospitalRepository.findById(anyLong())).thenReturn(Optional.of(hospital));
//        Assert.assertEquals(this.hospitalService.find(anyLong()), Optional.of(hospital));
//    }


//    @Test
//    void DeveFazerOUpdateDoHospital() {
//        Hospital hospital = Mockito.mock(Hospital.class);
//        when(this.hospitalRepository.findById(anyLong())).thenReturn(Optional.of(hospital));
//        when(this.hospitalRepository.save(hospital)).thenReturn(hospital);
//        Assert.assertTrue(this.hospitalService.update(anyLong(), hospital));
//
//    }
//
//    @Test
//    void deveRetornarFalsoCasoHospitalNaoExista() {
//        when(this.hospitalRepository.findById(anyLong())).thenReturn(Optional.empty());
//        Assert.assertFalse(this.hospitalService.update(anyLong(), new Hospital()));
//    }

//    @Test
//    void deveDeletarUmHospitalPeloId() {
//        when(this.hospitalRepository.findById(anyLong()))
//                .thenReturn(Optional.of(Mockito.mock(Hospital.class)));
//        Assert.assertTrue(this.hospitalService.delete(anyLong()));
//    }

//    @Test
//    void deveRetornaFalsoCasoNaoExistaHospitalAoDeletar() {
//        when(this.hospitalRepository.findById(anyLong())).thenReturn(Optional.empty());
//        Assert.assertFalse(this.hospitalService.delete(anyLong()));
//    }
}