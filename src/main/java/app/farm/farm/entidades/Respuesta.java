package app.farm.farm.entidades;

public class Respuesta <T> {
	private String mensaje;
	private String code;
	private T informacion;
	
	public Respuesta() {
		informacion=null;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public T getInformacion() {
		return informacion;
	}

	public void setInformacion(T informacion) {
		this.informacion = informacion;
	}
	
}
