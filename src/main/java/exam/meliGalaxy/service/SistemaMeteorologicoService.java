package exam.meliGalaxy.service;

import exam.meliGalaxy.model.Coordenada;
import exam.meliGalaxy.model.Planeta;
import exam.meliGalaxy.service.dto.ConsultaClimaDiaResponseDTO;
import exam.meliGalaxy.service.dto.ConsultaDiasResponseDTO;
import exam.meliGalaxy.service.dto.DiasLluviaResponseDTO;

/**
 * @author cristianlopez
 *
 */
public interface SistemaMeteorologicoService {
	
	public void inicializar(Planeta vulcano, Planeta ferengi, Planeta betasoide);
	
	public ConsultaDiasResponseDTO getDiasDeSequia();
	
	public DiasLluviaResponseDTO getDiasLluviaYMaximaPrecipitacion();
	
	public ConsultaDiasResponseDTO getDiasOptimos();
	
	public ConsultaClimaDiaResponseDTO getClimaDelDia(Integer dia);
	
	public Boolean esRecta(Coordenada c1, Coordenada c2, Coordenada incognita);

	Boolean formanUnTrianguloYPuntoPertenece(Coordenada punto, Coordenada vulcano, Coordenada ferengi, Coordenada betasoide);
	
}
