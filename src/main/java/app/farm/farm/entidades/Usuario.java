package app.farm.farm.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Este se ocupa para decir que es un campo clave ID
	@Column(name = "numEmpleado")
	String numEmpleado;
	@Column(name = "idRol")
	Integer idRol;
	@Column(name = "nombre")
	String nombre;
	@Column(name = "apellido")
	String apellido;
	@Column(name = "telefono")
	String telefono;
	@Column(name = "correo")
	String correo;
	@Column(name = "password")
	String password;
	@Column(name = "estatus")
	int estatus;

	@JsonManagedReference
	@OneToMany(mappedBy = "usuario", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH, }, fetch = FetchType.EAGER)
	private List<Evidencia> evidencias;

	public List<Evidencia> getEvidencias() {
		return evidencias;
	}

	public void setEvidencias() {
		this.evidencias = evidencias;
	}

	public String getNumEmpleado() {
		return numEmpleado;
	}

	public void setNumEmpleado(String numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Usuario(String numEmpleado, String password) {
		this.numEmpleado = numEmpleado;
		this.password = password;
	}

	public Usuario() {
	}

	@Override
	public String toString() {
		return "Usuario [numEmpleado=" + numEmpleado + ", idRol=" + idRol + ", nombre=" + nombre + ", apellido="
				+ apellido + ", telefono=" + telefono + ", correo=" + correo + ", password=" + password + ", estatus="
				+ estatus + ", evidencias=" + evidencias + "]";
	}
	
	
	
}
