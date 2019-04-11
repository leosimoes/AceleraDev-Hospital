package gestao.utils.Geolocalizacao;

import gestao.models.hospital.Endereco;

public interface InterfaceGeolocalizacaoAPI {
    public Coordenadas buscarCoordenadaDoEndereco(Endereco endereco);
}
