package gestao.repositories.hospital;

import gestao.models.hospital.Hospital;
import gestao.utils.geolocalizacao.Coordenadas;

import java.util.List;

public interface HospitalGeoRepository {
     public List<Hospital> buscarMaisProximosPorGeo(Coordenadas coordenadas);

}
