package app.farm.farm.controlador;

import java.util.List;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import app.farm.farm.DAO.EvidenciaInterfaz;
import app.farm.farm.DAO.UsuarioInterfaz;
import app.farm.farm.entidades.Evidencia;
import app.farm.farm.entidades.Respuesta;
import app.farm.farm.entidades.Usuario;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/appEntregas")
public class RequestController {

	@Autowired
	private UsuarioInterfaz user;

	@Autowired
	private EvidenciaInterfaz evi;

	// Metodo para hacer login

	@PostMapping(path = "/login", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Respuesta<Usuario>> Login(@RequestBody Usuario userLogin, HttpSession session) {

		Usuario miUsuario = user.validaUsuario(userLogin);
		System.out.println(miUsuario);
		if (miUsuario == null) {
			Respuesta<Usuario> res=new Respuesta<Usuario>();
			res.setInformacion(null);
			res.setCode("40");
			res.setMensaje("No se encontro usuario en el sistema");
			return new ResponseEntity<Respuesta<Usuario>>(res,HttpStatus.BAD_REQUEST);
		} else {
			// System.out.println("Todo bien");
			session.setAttribute("userVa", miUsuario);
			Usuario usuarioValidado = new Usuario();
			usuarioValidado.setNumEmpleado(miUsuario.getNumEmpleado());
			usuarioValidado.setNombre(miUsuario.getNombre());
			usuarioValidado.setApellido(miUsuario.getApellido());
			usuarioValidado.setEstatus(miUsuario.getEstatus());
			usuarioValidado.setIdRol(miUsuario.getIdRol());
			Respuesta<Usuario> res=new Respuesta<Usuario>();
			res.setInformacion(usuarioValidado);
			res.setCode("200");
			res.setMensaje("Se ingreso correctamente al sistema");
			return new ResponseEntity<Respuesta<Usuario>>(res,HttpStatus.OK);
		}
	}

	// Metodo encargado de devolver a todos los usuarios

	@PostMapping(path = "/allUsers", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Usuario>> listaUsuarios(HttpSession session) {
		if(session.getAttribute("userVa")!=null) {
			Usuario userTemp = (Usuario) session.getAttribute("userVa");
			if (userTemp.getIdRol() == 1) {
				List<Usuario> listaUsuarios;
				listaUsuarios = user.AllUsers();
				return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Usuario>>(HttpStatus.UNAUTHORIZED);
			}
				
		}else {
			return new ResponseEntity<List<Usuario>>(HttpStatus.UNAUTHORIZED);
		}
			
	}

	// Metodo encargado de devolver la lista de evidencias de un usuario

	@PostMapping(path = "/evidencias", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Evidencia>> listaEvidencias(HttpSession session) {
		if(session.getAttribute("userVa")!=null) {
			Usuario userTemp = (Usuario) session.getAttribute("userVa");
			List<Evidencia> evidencias;
			evidencias = evi.listaEvidencia(userTemp);
			return new ResponseEntity<List<Evidencia>>(evidencias, HttpStatus.OK);
		}else
			return new ResponseEntity<List<Evidencia>>(HttpStatus.UNAUTHORIZED);
	}

	// Metodo para agregar una evidencia

	@PostMapping(path = "/agregarEvidencia", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Respuesta<Usuario>> agregarEvidencia(@RequestBody Evidencia evidencia, HttpSession session) {

		Usuario userTemp = (Usuario) session.getAttribute("userVa");

		if (userTemp == null) {
			Respuesta<Usuario> res=new Respuesta<Usuario>();
			res.setInformacion(null);
			res.setCode("401");
			res.setMensaje("Se requiere iniciar sesión");
			return new ResponseEntity<Respuesta<Usuario>>(res, HttpStatus.UNAUTHORIZED);
		} else {
			try {
				
				GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
				
				//evidencia.setFecha(cal.getTime());
				evidencia.setUsuario(userTemp);
				evi.agregaEvidencia(evidencia);
				Respuesta<Usuario> res=new Respuesta<Usuario>();
				res.setInformacion(null);
				res.setCode("200");
				res.setMensaje("Se agregó evidencia correctamente");
				return new ResponseEntity<Respuesta<Usuario>>(res, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Respuesta<Usuario>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	// Metodo encargado de devolver la lista de evidencias de un usuario

	@PostMapping(path = "/bitacora", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Evidencia>> muestraBitacora(HttpSession session) {
		Usuario userTemp = (Usuario) session.getAttribute("userVa");
		if (userTemp != null) {
			if (userTemp.getIdRol() == 1) {
				List<Evidencia> evidencias;
				
				evidencias = evi.muestraBitacora();
				System.out.println(evidencias.get(1).getUsuario().getNumEmpleado());
				System.out.println(evidencias.get(1).getUsuario().getNombre());
				return new ResponseEntity<List<Evidencia>>(evidencias, HttpStatus.OK);
			} else
				return new ResponseEntity<List<Evidencia>>(HttpStatus.UNAUTHORIZED);
		} else
			return new ResponseEntity<List<Evidencia>>(HttpStatus.BAD_REQUEST);
	}
	
	// Metodo encargado de cerrar sesion

	@GetMapping("/cerrar")
	public ResponseEntity<String> cerrarSession(HttpSession session) {
		session.removeAttribute("userVa");
		if(session.getAttribute("userVa")==null) {
			return new ResponseEntity<String>("Sesión cerrada conrrectamente", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Algo salio mal", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/seguridad")
	public ResponseEntity<String> seguridad() {
		System.out.println("Dentro"); 
	return new ResponseEntity<String>("Todo bien", HttpStatus.OK);
	}
	
}
