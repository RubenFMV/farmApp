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
	Integer idbase;
	String numEmpleado;
	Integer idRol;
	String nombre;
	String apellido;
	String telefono;
	String correo;
	String password;
	int estatus;

	@OneToMany(mappedBy = "usuario")
	private List<Evidencia> evidencias;

	public List<Evidencia> getEvidencias() {
		return evidencias;
	}

	public void setEvidencias() {
		this.evidencias = evidencias;
	}
	
	
	
	public Integer getIdbase() {
		return idbase;
	}

	public void setIdbase(Integer idbase) {
		this.idbase = idbase;
	}

	public void setEvidencias(List<Evidencia> evidencias) {
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
