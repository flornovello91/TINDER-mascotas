package tinder.mascotas.tinder.servicios;

import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tinder.mascotas.tinder.entidades.Usuario;
import tinder.mascotas.tinder.exception.MyException;
import tinder.mascotas.tinder.repositorios.RepositorioUsuario;

@Service
public class UsuarioServicio {
    
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    
    public void registrar(String nombre, String apellido,String mail,String clave) throws MyException{
        
        validacion(nombre,apellido,mail,clave);
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setMail(mail);
        usuario.setClave(clave);
        usuario.setAlta(new Date());
        
        repositorioUsuario.save(usuario);
    }
    
    public void modificar(String id,String nombre, String apellido,String clave,String mail) throws MyException{
        validacion(nombre,apellido,mail,clave);
        
        Optional<Usuario> respuesta = repositorioUsuario.findById(id);
        
        if (respuesta.isPresent()){
            Usuario usuario = respuesta.get();
            
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setClave(clave);
            usuario.setMail(mail);
            
            repositorioUsuario.save(usuario);
        }else {
            throw new MyException("No se encontró el usuario solicitado.");
        }

    }
    
    public void deshabilitar(String id) throws MyException{
        Optional<Usuario> respuesta = repositorioUsuario.findById(id);
        
        if (respuesta.isPresent()){
            Usuario usuario = respuesta.get();
            usuario.setBaja(new Date());
            repositorioUsuario.save(usuario);
        }else {
            throw new MyException("No se encontró el usuario solicitado.");
        }
    }
    
    public void habilitar(String id) throws MyException{
        Optional<Usuario> respuesta = repositorioUsuario.findById(id);
        
        if (respuesta.isPresent()){
            Usuario usuario = respuesta.get();
            usuario.setBaja(null);
            repositorioUsuario.save(usuario);
        }else {
            throw new MyException("No se encontró el usuario solicitado.");
        }
    }
    
    public void validacion (String nombre, String apellido,String mail,String clave) throws MyException{
        if (nombre == null || nombre.isEmpty()){
            throw new MyException("El nombre del usuario no puede ser nulo.");
        }
        if (apellido == null || apellido.isEmpty()){
            throw new MyException("El apellido del usuario no puede ser nulo.");
        }
        if (mail == null || mail.isEmpty()){
            throw new MyException("El mail del usuario no puede ser nulo.");
        }
        if (clave == null || nombre.isEmpty() || clave.length()<=6){
            throw new MyException("Clave inválida.");
        }
    }
}
