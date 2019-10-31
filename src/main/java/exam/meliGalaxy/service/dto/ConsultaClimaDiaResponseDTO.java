package exam.meliGalaxy.service.dto;

import exam.meliGalaxy.model.Clima;

public class ConsultaClimaDiaResponseDTO {
	
	private Integer dia;
	private Clima clima;
	
	public ConsultaClimaDiaResponseDTO(Integer dia, Clima clima) {
		super();
		this.dia = dia;
		this.clima = clima;
	}
	
	public Integer getDia() {
		return dia;
	}
	public Clima getClima() {
		return clima;
	}
	public void setDia(Integer dia) {
		this.dia = dia;
	}
	public void setClima(Clima clima) {
		this.clima = clima;
	}


}
