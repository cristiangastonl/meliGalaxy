/**
 *  Clase que modela a un planeta en el sistema.
 */
package exam.meliGalaxy.model;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author cristianlopez
 *
 */
public class Planeta {
	
	@Value("${galaxia.dias.ano}")
	private int diasAño = 360;
	
	private String name;
	private Integer velocidadAngular;
	private Integer distanciaAlSol;
	private Integer sentidoHorario;
	
	public Planeta(String name, Integer velocidadAngular, Integer distanciaAlSol, Integer sentidoHorario) {
		this.name = name;
		this.velocidadAngular = velocidadAngular;
		this.distanciaAlSol = distanciaAlSol;
		this.sentidoHorario = sentidoHorario;
	}

	public Coordenada getCoordenada(Integer dia){
		
		Double anguloEnRadianes = Math.toRadians((dia * this.velocidadAngular * sentidoHorario) % diasAño);
		
		Double x = Math.cos(anguloEnRadianes) * this.distanciaAlSol;
		Double y = Math.sin(anguloEnRadianes) * this.distanciaAlSol;
		Coordenada result = new Coordenada(x, y);
		return result;

	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the velocidadAngular
	 */
	public Integer getVelocidadAngular() {
		return velocidadAngular;
	}


	/**
	 * @param velocidadAngular the velocidadAngular to set
	 */
	public void setVelocidadAngular(Integer velocidadAngular) {
		this.velocidadAngular = velocidadAngular;
	}


	/**
	 * @return the distanciaAlSol
	 */
	public Integer getDistanciaAlSol() {
		return distanciaAlSol;
	}


	/**
	 * @param distanciaAlSol the distanciaAlSol to set
	 */
	public void setDistanciaAlSol(Integer distanciaAlSol) {
		this.distanciaAlSol = distanciaAlSol;
	}


	/**
	 * @return the sentidoHorario
	 */
	public Integer getSentidoHorario() {
		return sentidoHorario;
	}


	/**
	 * @param sentidoHorario the sentidoHorario to set
	 */
	public void setSentidoHorario(Integer sentidoHorario) {
		this.sentidoHorario = sentidoHorario;
	}


}
