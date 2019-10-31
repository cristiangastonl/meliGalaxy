package exam.meliGalaxy.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import exam.meliGalaxy.model.Clima;
import exam.meliGalaxy.model.Coordenada;
import exam.meliGalaxy.model.Planeta;
import exam.meliGalaxy.model.Pronostico;
import exam.meliGalaxy.repository.PronosticoRepository;
import exam.meliGalaxy.service.dto.ConsultaClimaDiaResponseDTO;
import exam.meliGalaxy.service.dto.ConsultaDiasResponseDTO;
import exam.meliGalaxy.service.dto.DiasLluviaResponseDTO;
import exam.meliGalaxy.service.dto.MaximaPrecipitacionInfo;
import exam.meliGalaxy.service.dto.PronosticoDTO;

/**
 * @author cristianlopez
 *
 */
@Service
public class SistemaMeteorologicoServiceImpl implements SistemaMeteorologicoService {
	
  private final static Logger logger = LoggerFactory.getLogger(SistemaMeteorologicoServiceImpl.class);
  
	@Value("${galaxia.dias.ano}")
	private Integer diasAño;
	
	private Integer diasDeSequia;
	private Integer diasOptimos;
	private Integer diasLluvia;
	private MaximaPrecipitacionInfo diaMaximaPrecipitacion;
	
	private PronosticoRepository pronosticoRepository;
	private PronosticoToPronosticoDTOConverter pronosticoToPronosticoDTOConverter;
	
	@Autowired
	public SistemaMeteorologicoServiceImpl(PronosticoRepository pronosticoRepository,
																				 PronosticoToPronosticoDTOConverter pronosticoToPronosticoDTOConverter) {
		super();
		this.pronosticoRepository = pronosticoRepository;
		this.pronosticoToPronosticoDTOConverter = pronosticoToPronosticoDTOConverter;
	}

	public SistemaMeteorologicoServiceImpl() {
	}

	@Override
	@Transactional
	public void inicializar(Planeta vulcano, Planeta ferengi, Planeta betasoide) {
		logger.info("... Recolectando pronosticos de los proximos 10 años...");
		
		pronosticoRepository.deleteAll();
		diaMaximaPrecipitacion = new MaximaPrecipitacionInfo(0, 0.0);
		diasDeSequia = 0;
		diasOptimos = 0;
		diasLluvia = 0;
		
		for (int i = 0;i < diasAño*10; i++) {
			Pronostico pronosticoDelDia = this.calcularPronosticoDelDia(i, vulcano, ferengi, betasoide);
			logger.info(pronosticoDelDia.toString());
			pronosticoRepository.save(pronosticoDelDia);
		}
		
		logger.info("... Finalizó la recoleccion de pronosticos de los proximos 10 años...");
	}


	@Override
	public ConsultaDiasResponseDTO getDiasDeSequia() {
		return new ConsultaDiasResponseDTO(diasDeSequia);
	}

	@Override
	public DiasLluviaResponseDTO getDiasLluviaYMaximaPrecipitacion() {
		return new DiasLluviaResponseDTO(diasLluvia, diaMaximaPrecipitacion.getPrecipitacion());
	}

	@Override
	public ConsultaDiasResponseDTO getDiasOptimos() {
		return new ConsultaDiasResponseDTO(diasOptimos);
	}

	@Override
	public ConsultaClimaDiaResponseDTO getClimaDelDia(Integer dia) {
		Optional<Pronostico> pronostico = pronosticoRepository.findById(dia);
		PronosticoDTO pronosticoDTO = pronosticoToPronosticoDTOConverter.convert(pronostico);
		ConsultaClimaDiaResponseDTO consultaClimaDiaResponsableDTO = new ConsultaClimaDiaResponseDTO(pronosticoDTO.getDia(), pronosticoDTO.getClima());
		return consultaClimaDiaResponsableDTO;
	}

	@Override
	public Boolean esRecta(Coordenada c1, Coordenada c2, Coordenada incognita) {
		Double pendiente = (c2.getY() - c1.getY()) / (c2.getX() - c1.getX());
		Double valorRecta = Math.abs(pendiente * (incognita.getX() - c1.getX()) - (incognita.getY() - c1.getY()));
		return valorRecta.equals(0.0);
	}
	@Override
	public Boolean formanUnTrianguloYPuntoPertenece(Coordenada punto, Coordenada vulcano, Coordenada ferengi,
			Coordenada betasoide) {
		Boolean rectaNoTieneLosTresPlanetas = !this.esRecta(vulcano, ferengi, betasoide);
		Boolean solDentroDelTriangulo = this.solDentroDeTriangulo(vulcano, ferengi, betasoide, punto);
		return rectaNoTieneLosTresPlanetas && solDentroDelTriangulo;
	}
	
	/*********************************METODOS AUXILIARES********************************/
	
	private Pronostico calcularPronosticoDelDia(int dia, Planeta vulcano, Planeta ferengi, Planeta betasoide) {
//		int diaResto = dia % 360;
		Coordenada sol = new Coordenada (0.0, 0.0);
		Boolean alineadosEntreSiySol = this.formanUnaRectaConElSol(sol, vulcano.getCoordenada(dia), ferengi.getCoordenada(dia), betasoide.getCoordenada(dia));
		Boolean noAlineadosEntreSiyContieneSol = this.formanUnTrianguloYPuntoPertenece(sol, vulcano.getCoordenada(dia), ferengi.getCoordenada(dia), betasoide.getCoordenada(dia));
		Boolean alineadosEntreSiyNoSol = this.formanUnaRectaSinElSol(sol, vulcano.getCoordenada(dia), ferengi.getCoordenada(dia), betasoide.getCoordenada(dia));
		Pronostico pronosticoResultado = new Pronostico(dia, Clima.TEMPLADO, 0.0);
		
		if (alineadosEntreSiySol){
			pronosticoResultado = new Pronostico(dia, Clima.SEQUIA, 0.0);
			diasDeSequia++;
		} else if (noAlineadosEntreSiyContieneSol) {
			Double intensidad = this.calcularIntensidad(dia,sol,vulcano,ferengi,betasoide);
			if (intensidad > diaMaximaPrecipitacion.getPrecipitacion()){
				diaMaximaPrecipitacion.setDia(dia);
				diaMaximaPrecipitacion.setPrecipitacion(intensidad);
			}
			pronosticoResultado = new Pronostico(dia, Clima.LLUVIA, intensidad);
			diasLluvia++;
		} else if (alineadosEntreSiyNoSol){
			pronosticoResultado = new Pronostico(dia, Clima.OPTIMO, 0.0);
			diasOptimos++;
		} 
		return pronosticoResultado;
	}

	private Double calcularIntensidad(int dia, Coordenada sol, Planeta vulcano, Planeta ferengi, Planeta betasoide) {
		Coordenada cFerengi = ferengi.getCoordenada(dia);
		Coordenada cVulcano = vulcano.getCoordenada(dia);
		Coordenada cBetasoide = betasoide.getCoordenada(dia);
		return cFerengi.distancia(cVulcano) + cVulcano.distancia(cBetasoide) + cBetasoide.distancia(cFerengi);
	}

	private Boolean formanUnaRectaSinElSol(Coordenada sol, Coordenada vulcano, Coordenada ferengi,
	    Coordenada betasoide) {
		Boolean rectaTieneLosTresPlanetas = this.esRecta(vulcano, ferengi, betasoide);
		Boolean rectaNoPasaPorElSol = !this.esRecta(vulcano, ferengi, sol);
		return rectaTieneLosTresPlanetas && rectaNoPasaPorElSol;
	}

	private Boolean formanUnaRectaConElSol(Coordenada sol, Coordenada vulcano, Coordenada ferengi,
			Coordenada betasoide) {
		Boolean rectaTieneLosTresPlanetas = this.esRecta(vulcano, ferengi, betasoide);
		Boolean rectaPasaPorElSol = this.esRecta(vulcano, ferengi, sol);
		return rectaTieneLosTresPlanetas && rectaPasaPorElSol;
	}
	

	private Boolean solDentroDeTriangulo(Coordenada vulcano, Coordenada ferengi, Coordenada betasoide, Coordenada sol) {
		Boolean trianguloPlanetasPositivo = this.calcularPositividadTriangulos(vulcano, ferengi, betasoide);
		Boolean trianguloPlanetasconSolPositivo = this.calcularPositividadTriangulos(vulcano, ferengi, sol);
		Boolean trianguloPlanetasconSolPositivo1 = this.calcularPositividadTriangulos(ferengi, betasoide, sol);
		Boolean trianguloPlanetasconSolPositivo2 = this.calcularPositividadTriangulos(betasoide, vulcano, sol);
		Boolean result = trianguloPlanetasPositivo && trianguloPlanetasconSolPositivo && trianguloPlanetasconSolPositivo1 && trianguloPlanetasconSolPositivo2;
		
		if (!trianguloPlanetasPositivo) {
			// si no es positivo, entonces todos tienen que ser negativos para que se cumpla.
			result = !trianguloPlanetasPositivo && !trianguloPlanetasconSolPositivo && !trianguloPlanetasconSolPositivo1 && !trianguloPlanetasconSolPositivo2;
		}
		return result;
	}

	private Boolean calcularPositividadTriangulos(Coordenada vulcano, Coordenada ferengi, Coordenada betasoide) {
		Double componente1 = (vulcano.getX() - betasoide.getX()) * (ferengi.getY() - betasoide.getY());
		Double componente2 = (vulcano.getY() - betasoide.getY()) * (ferengi.getX() - betasoide.getX());
		return  componente1 - componente2 >= 0;
	}

	
	
}
