package exam.meliGalaxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import exam.meliGalaxy.model.Planeta;
import exam.meliGalaxy.service.SistemaMeteorologicoService;

@SpringBootApplication
public class MeliGalaxyApplication {

  private final static Logger logger = LoggerFactory.getLogger(MeliGalaxyApplication.class);
	private static final String FERENGI = "ferengi";
	private static final String BETASOIDE = "betasoide";
	private static final String VULCANO = "vulcano";
	
	@Autowired
	private SistemaMeteorologicoService sistemaMeteorologicoService;
	
	public static void main(String[] args) {
		SpringApplication.run(MeliGalaxyApplication.class, args);
	}

	@Bean
	public CommandLineRunner init() {
		return (args) -> {
			logger.info("...Comenzando la carga de datos...");
			
			int sentidoHorario = 1;
			int sentidoAntiHorario = -1;
			Planeta planeta1 = new Planeta(FERENGI, 1, 500, sentidoHorario);
			Planeta planeta2 = new Planeta(BETASOIDE, 3, 2000, sentidoHorario);
			Planeta planeta3 = new Planeta(VULCANO, 5, 1000, sentidoAntiHorario);
				
			sistemaMeteorologicoService.inicializar(planeta1, planeta2, planeta3);
			
			logger.info("...Finalizada la carga de datos...");
		};
	}
	
}
