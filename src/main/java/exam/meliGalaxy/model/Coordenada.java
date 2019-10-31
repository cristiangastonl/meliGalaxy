package exam.meliGalaxy.model;

public class Coordenada {
	
	private Double x;
	private Double y;
	
	public Coordenada(Double x, Double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public Double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(Double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public Double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(Double y) {
		this.y = y;
	}
	
	public double distancia (Coordenada coordenada){
		return Math.sqrt(Math.pow(coordenada.getX()-this.x, 2) + Math.pow(coordenada.getY()-this.y, 2));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coordenada [x=" + x + ", y=" + y + "]";
	}

	
}
