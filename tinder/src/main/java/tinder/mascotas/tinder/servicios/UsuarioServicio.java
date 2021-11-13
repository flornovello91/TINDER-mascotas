package tinder.mascotas.tinder.servicios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tinder.mascotas.tinder.entidades.Usuario;
import tinder.mascotas.tinder.entidades.Foto;
import tinder.mascotas.tinder.exception.MyException;
import tinder.mascotas.tinder.repositorios.RepositorioUsuario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UsuarioServicio {
    
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    
    @Autowired
    private FotoServicio fotoServicio;
    
//    @Autowired
//    private NotificacionServicio notificacionServicio;
    
    @Transactional
    public void registrar(MultipartFile archivo,String nombre, String apellido,String mail,String clave) throws MyException, IOException{
        
        validacion(nombre,apellido,mail,clave);
        
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setMail(mail);
        
        String encriptada = new BCryptPasswordEncoder().encode(clave);
        usuario.setClave(encriptada);
        
        usuario.setAlta(new Date());
        
        Foto foto = fotoServicio.guardar(archivo);
        usuario.setFoto(foto);
        
        repositorioUsuario.save(usuario);
//        notificacionServicio.enviarMail("Bienvenidos al Tinder de mascotas!", "Tinder de Mascota", usuario.getMail());
    }
    
    @Transactional
    public void modificar(MultipartFile archivo,String id,String nombre, String apellido,String clave,String mail) throws MyException, IOException{
        validacion(nombre,apellido,mail,clave);
        
        Optional<Usuario> respuesta = repositorioUsuario.findById(id);
        
        if (respuesta.isPresent()){
            Usuario usuario = respuesta.get();
            
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            
            String encriptada = new BCryptPasswordEncoder().encode(clave);
            usuario.setClave(encriptada);
            usuario.setClave(clave);
            
            usuario.setMail(mail);
            
            String idFoto = null;
            if (usuario.getFoto() != null){
                idFoto = usuario.getFoto().getId();
            }
            Foto foto = fotoServicio.actualizar(idFoto, archivo);
            usuario.setFoto(foto);
             
            repositorioUsuario.save(usuario);
        }else {
            throw new MyException("No se encontró el usuario solicitado.");
        }

    }
    
    @Transactional
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
    
    @Transactional
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
    
//    //Este metodo es llamado cuando el usuario quiere autentificarse en la plataforma (formulario de login)
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
// 
//        if(username.equals("test")) {
// 
//            return User.withDefaultPasswordEncoder()
//                       .username("test")
//                       .password("test")
//                       .roles("test")
//                       .build();
//        } else {
//            throw new UsernameNotFoundException(username);
//        }
//    }
//    @Override
//    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {  
//        Usuario usuario = repositorioUsuario.BuscarPorMail(mail);
//        if (usuario != null){
//            
//            List <GrantedAuthority> permisos = new ArrayList<>();
//            
//            GrantedAuthority p1 = new SimpleGrantedAuthority("MODULO_FOTOS");
//            permisos.add(p1);
//            
//            GrantedAuthority p2 = new SimpleGrantedAuthority("MODULO_MASCOTAS");
//            permisos.add(p2);
//            
//            GrantedAuthority p3 = new SimpleGrantedAuthority("MODULO_VOTOS");
//            permisos.add(p3);
//            
//            User user = new User(usuario.getNombre(),usuario.getClave(), permisos);
//            return user;
//        }else{
//            return null;
//        }
//    }
}
