package app.farm.farm.entidades;

import java.util.Calendar;
import java.util.Date;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "evidencia")
public class Evidencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Este se ocupa para decir que es un campo clave ID
	@Column(name="idEvidencia")
	private Integer idEvidencia;
	
	@Column(name="fecha")
	private Calendar fecha;
	
	@Column(name="imagen")
	private String imagen;
	
	@Column(name="folio")
	private String folio;
	
	@JsonBackReference
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH,})
	@JoinColumn(name="numEmpleado")
	private Usuario usuario;

	
	public Evidencia() {	}
	
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	
	public Integer getIdEvidencia() {
		return idEvidencia;
	}
	
	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar date) {
		this.fecha = date;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "evidencia [idEvidencia=" + idEvidencia + ", fecha=" + fecha + ", imagen=" + imagen + ", folio=" + folio
				+  "]";
	}
}
