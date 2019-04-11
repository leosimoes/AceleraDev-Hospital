package gestao.utils.geolocalizacao;

import gestao.models.hospital.Endereco;

public interface InterfaceGeolocalizacaoAPI {
    public Coordenadas buscarCoordenadaDoEndereco(Endereco endereco);
}
