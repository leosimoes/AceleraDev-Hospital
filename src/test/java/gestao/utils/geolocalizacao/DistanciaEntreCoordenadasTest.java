package gestao.utils.geolocalizacao;

import org.junit.jupiter.api.Test;

class DistanciaEntreCoordenadasTest {




    @Test
    void calcMetrosTest() {

        Double distance = new Coordenadas(-46.8754859,-23.6821604).distancia(new Coordenadas(-44.0340902,-19.9027026));
        System.out.println(distance);
    }
}