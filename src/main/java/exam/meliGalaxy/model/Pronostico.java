package exam.meliGalaxy.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

import exam.meliGalaxy.repository.ClimaConverter;

@Entity
public class Pronostico {

	@Id
	private Integer dia;
	
  @Column(name = "clima", nullable = false)
  @Convert(converter = ClimaConverter.class)
	private Clima clima;
  
  @Column(name = "intensidad")
  private Double intensidadLluvia;
  
  public Pronostico() {
	}

  public Pronostico(Integer dia, Clima clima, Double intensidadLluvia) {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pronostico [dia=" + dia + ", clima=" + clima + ", intensidadLluvia=" + intensidadLluvia + "]";
	}
  
    
}
