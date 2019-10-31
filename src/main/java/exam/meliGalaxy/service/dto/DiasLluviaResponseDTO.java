package exam.meliGalaxy.service.dto;

public class DiasLluviaResponseDTO {
	
	private Integer dias;
	private Double picoMaximoLluvia;

	public DiasLluviaResponseDTO(Integer dias, Double picoMaximoLluvia) {
		super();
		this.dias = dias;
		this.picoMaximoLluvia = picoMaximoLluvia;
	}

	public Integer getDias() {
		return dias;
	}

	public Double getPicoMaximoLluvia() {
		return picoMaximoLluvia;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public void setPicoMaximoLluvia(Double picoMaximoLluvia) {
		this.picoMaximoLluvia = picoMaximoLluvia;
	}
}
