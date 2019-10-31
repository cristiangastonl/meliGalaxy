package exam.meliGalaxy.service.dto;

import exam.meliGalaxy.model.Clima;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PronosticoDTO {
	
	private Integer dia;
	private Clima clima;
	@JsonIgnore
  private Double intensidadLluvia;

  public PronosticoDTO(Integer dia, Clima clima, Double intensidadLluvia) {
  	super();
  	this.dia = dia;
  	this.clima = clima;
  	this.intensidadLluvia = intensidadLluvia;
  }
  
	/**
	 * @return the dia
	 */
	public Integer getDia() {
		return dia;
	}

	/**
	 * @param dia the dia to set
	 */
	public void setDia(Integer dia) {
		this.dia = dia;
	}

	/**
	 * @return the clima
	 */
	public Clima getClima() {
		return clima;
	}

	/**
	 * @param clima the clima to set
	 */
	public void setClima(Clima clima) {
		this.clima = clima;
	}

	/**
	 * @return the intensidadLluvia
	 */
	public Double getIntensidadLluvia() {
		return intensidadLluvia;
	}

	/**
	 * @param intensidadLluvia the intensidadLluvia to set
	 */
	public void setIntensidadLluvia(Double intensidadLluvia) {
		this.intensidadLluvia = intensidadLluvia;
	}

}
