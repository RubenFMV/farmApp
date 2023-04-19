package app.farm.farm.DAO;

import java.util.List;

import app.farm.farm.entidades.Evidencia;
import app.farm.farm.entidades.Usuario;


public interface EvidenciaInterfaz {
	public void agregaEvidencia(Evidencia LaEvidencia);
	public List<Evidencia> listaEvidencia(Usuario user);
	public List<Evidencia> muestraBitacora();
}
