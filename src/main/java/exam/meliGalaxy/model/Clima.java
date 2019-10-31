package exam.meliGalaxy.model;

public enum Clima {
	LLUVIA("lluvia"),
	SEQUIA("Sequia"),
	OPTIMO("Condiciones Optimas"),
	TEMPLADO("Templado");
	
	private String nombreOriginal;

	private Clima(String nombreOriginal) {
		this.setNombreOriginal(nombreOriginal);
	}

	public String getNombreOriginal() {
		return nombreOriginal;
	}
	
	public void setNombreOriginal(String nombreOriginal) {
		this.nombreOriginal = nombreOriginal;
	}

	public static Clima fromString(String clima) {
		switch(clima) {
		case "lluvia":
			return Clima.LLUVIA;
		case "Sequia":
			return Clima.SEQUIA;
		case "Condiciones Optimas":
			return Clima.OPTIMO;
		case "Templado":
			return Clima.TEMPLADO;
		default:
			throw new IllegalArgumentException("Identificador [" + clima
                    + "] not supported.");
		}
	}

}
