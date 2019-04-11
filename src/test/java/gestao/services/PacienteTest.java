package gestao.services;

import gestao.controllers.PacienteController;
import gestao.models.paciente.Paciente;
import gestao.models.paciente.SexoPacienteENUM;
import gestao.repositories.PacienteRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class PacienteTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService service;

    @InjectMocks
    private PacienteController controller;


    @Test
    public void deveSalvarUmPaciente() {
        BindingResult result = mock(BindingResult.class);
        Assert.assertTrue(service.salvaPaciente(pacienteValido(), result));
    }

    @Test
    public void salvaPacienteController() {
        BindingResult result = mock(BindingResult.class);

        Assert.assertEquals(controller.salvaPaciente(pacienteValido(), result), HttpStatus.CREATED);
    }

    @Test
    public void naoDeveSalvarUmPaciente() {
        BindingResult result = mock(BindingResult.class);
        Paciente paciente = pacienteRepository.save(pacienteInvalido());
        Assert.assertNull(paciente);
    }




    public Paciente pacienteValido() {
        Paciente paciente = new Paciente();
        paciente.setNome("Paciente1");
        paciente.setDataNascimento(LocalDate.parse("2000-01-01"));
        paciente.setCpf("141.537.357-44");
        paciente.setEmAtendimento(false);
        paciente.setSexo(SexoPacienteENUM.M);
        return paciente;
    }

    public Paciente pacienteInvalido() {
        Paciente paciente = new Paciente();
        paciente.setNome("Paciente1");
        paciente.setDataNascimento(LocalDate.parse("2000-01-01"));
        paciente.setCpf("141.537.357-44");
        paciente.setEmAtendimento(false);
        paciente.setSexo(SexoPacienteENUM.M);
        return paciente;
    }
}
