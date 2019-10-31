package exam.meliGalaxy.repository;

import javax.persistence.AttributeConverter;

import exam.meliGalaxy.model.Clima;

public class ClimaConverter implements AttributeConverter<Clima, String> {

	@Override
	public String convertToDatabaseColumn(Clima clima) {
		return clima == null ? null : clima.getNombreOriginal();
	}

	@Override
	public Clima convertToEntityAttribute(String clima) {
        return clima == null ? null : Clima.fromString(clima);
	}

}
