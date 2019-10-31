package exam.meliGalaxy.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exam.meliGalaxy.service.SistemaMeteorologicoService;
import exam.meliGalaxy.service.dto.ConsultaClimaDiaResponseDTO;
import exam.meliGalaxy.service.dto.ConsultaDiasResponseDTO;
import exam.meliGalaxy.service.dto.DiasLluviaResponseDTO;

@RestController
@RequestMapping("/galaxy")
@Validated
public class MeliGalaxyController{
	
  private final static Logger logger = LoggerFactory.getLogger(MeliGalaxyController.class);
	
	private SistemaMeteorologicoService sistemaMeteorologicoService;
	
	public MeliGalaxyController(SistemaMeteorologicoService sistemaMeteorologicoService) {
		super();
		this.sistemaMeteorologicoService = sistemaMeteorologicoService;
	}

	
  @GetMapping("/clima")
  public ConsultaClimaDiaResponseDTO getClimaByDate(@RequestParam(value="dia") @Min(0) @Max(359*10) Integer dia) {

  		logger.info("Consultando el clima del dia {}", dia);
  		
  		return sistemaMeteorologicoService.getClimaDelDia(dia);
  }
  
  @GetMapping("/clima/dias_sequia")
  public ConsultaDiasResponseDTO getCantDiasSequia() {
  	
  		logger.info("Consultando la cantidad de dias de sequia.");
  		
  		return sistemaMeteorologicoService.getDiasDeSequia();
  }

  @GetMapping("/clima/dias_optimos")
  public ConsultaDiasResponseDTO getCantDiasOptimos() {
  	
  		logger.info("Consultando la cantidad de dias en optimas condiciones.");
  		
  		return sistemaMeteorologicoService.getDiasOptimos();
  }
  
  @GetMapping("/clima/dias_max_precipitacion")
  public DiasLluviaResponseDTO getCantDiasYMaximaPrecipitacon() {
  	
  		logger.info("Consultando la cantidad de dias con lluvia y maxima precipitacion.");
  		
  		return sistemaMeteorologicoService.getDiasLluviaYMaximaPrecipitacion();
  }

  
}