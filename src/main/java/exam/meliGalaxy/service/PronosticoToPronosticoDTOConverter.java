package exam.meliGalaxy.service;

import java.util.Optional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import exam.meliGalaxy.model.Pronostico;
import exam.meliGalaxy.service.dto.PronosticoDTO;

@Component
public class PronosticoToPronosticoDTOConverter implements Converter<Optional<Pronostico>, PronosticoDTO> {

	@Override
	public PronosticoDTO convert(Optional<Pronostico> optionalPronostico) {
		PronosticoDTO pronosticoDto = null;
		if (optionalPronostico.isPresent()){
			Pronostico pronostico = optionalPronostico.get();
			pronosticoDto = new PronosticoDTO(pronostico.getDia(), pronostico.getClima(), pronostico.getIntensidadLluvia());
		}
		return pronosticoDto;
	}

}
