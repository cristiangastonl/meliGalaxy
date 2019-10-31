package exam.meliGalaxy.service.dto;

public class MaximaPrecipitacionInfo {
	
	public MaximaPrecipitacionInfo(Integer dia, Double precipitacion) {
		super();
		this.dia = dia;
		this.precipitacion = precipitacion;
	}
	private Integer dia;
	private Double precipitacion;
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
	 * @return the precipitacion
	 */
	public Double getPrecipitacion() {
		return precipitacion;
	}
	/**
	 * @param precipitacion the precipitacion to set
	 */
	public void setPrecipitacion(Double precipitacion) {
		this.precipitacion = precipitacion;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MaximaPrecipitacionInfo [dia=" + dia + ", precipitacion=" + precipitacion + "]";
	}
	

}
