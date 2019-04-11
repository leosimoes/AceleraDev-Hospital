package gestao.respositories.hospital;

import gestao.models.hospital.Hospital;
import gestao.utils.Geolocalizacao.Coordenadas;

import java.util.List;

public interface HospitalGeoRepository {
     public List<Hospital> buscarMaisProximosPorGeo(Coordenadas coordenadas);

}
