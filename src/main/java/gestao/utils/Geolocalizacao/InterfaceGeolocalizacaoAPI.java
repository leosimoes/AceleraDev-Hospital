package gestao.utils.Geolocalizacao;

import gestao.models.Endereco;

public interface InterfaceGeolocalizacaoAPI {
    public Coordenadas buscarPontoPorEndereco(Endereco endereco);
}
