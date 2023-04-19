package app.farm.farm.DAO;

import java.util.List;

import app.farm.farm.entidades.Usuario;

public interface UsuarioInterfaz {
	
	public Usuario validaUsuario(Usuario user);

	public List<Usuario> AllUsers();
}
