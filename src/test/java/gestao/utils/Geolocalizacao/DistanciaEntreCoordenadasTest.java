package gestao.utils.Geolocalizacao;

import gestao.services.HospitalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class DistanciaEntreCoordenadasTest {




    @Test
    void calcMetrosTest() {

        Double distance = new Coordenadas(-46.8754859,-23.6821604).distancia(new Coordenadas(-44.0340902,-19.9027026));
        System.out.println(distance);
    }
}