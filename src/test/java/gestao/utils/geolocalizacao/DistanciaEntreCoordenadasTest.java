package gestao.utils.geolocalizacao;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

class DistanciaEntreCoordenadasTest {


    @Test
    void calculaDistanciEntreCoordenadasTest() {
        Coordenadas origem = new Coordenadas(-46.8754859,-23.6821604);
        Coordenadas destino = new Coordenadas(-44.0340902,-19.9027026);
        assertNotNull(origem.distancia(destino));
    }

}