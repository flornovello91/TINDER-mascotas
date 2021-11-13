package tinder.mascotas.tinder.servicios;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tinder.mascotas.tinder.enumeraciones.Sexo;
import tinder.mascotas.tinder.entidades.Usuario;
import tinder.mascotas.tinder.entidades.Mascota;
import tinder.mascotas.tinder.entidades.Foto;
import tinder.mascotas.tinder.exception.MyException;
import tinder.mascotas.tinder.repositorios.RepositorioMascota;
import tinder.mascotas.tinder.repositorios.RepositorioUsuario;

@Service
public class MascotaServicio {
    
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    
    @Autowired
    private RepositorioMascota repositorioMascota;
    
    @Autowired
    private FotoServicio fotoServicio;
    
    @Transactional
    public void agregarMascota (MultipartFile archivo,String idUsuario,String nombre,Sexo sexo) throws MyException, IOException{
        
        Usuario usuario = repositorioUsuario.findById(idUsuario).get();
        
        validar(nombre,sexo);
        
        Mascota mascota = new Mascota();
        mascota.setNombre(nombre);
        mascota.setSexo(sexo);
        mascota.setAlta(new Date());
        
        Foto foto = fotoServicio.guardar(archivo);
        mascota.setFoto(foto);
        
        repositorioMascota.save(mascota);
    }
    
    @Transactional
    public void modificar (MultipartFile archivo,String idUsuario,String idMascota,String nombre,Sexo sexo) throws MyException, IOException{
        validar(nombre,sexo);
        
        Optional<Mascota>respuesta = repositorioMascota.findById(idMascota);
        if (respuesta.isPresent()){
            Mascota mascota = respuesta.get();
            if (mascota.getUsuario().getId().equals(idUsuario)){
                mascota.setNombre(nombre);
                mascota.setSexo(sexo);
                
                String idFoto = null;
                if (mascota.getFoto() != null){
                    idFoto = mascota.getId();
                }
                Foto foto = fotoServicio.actualizar(idFoto, archivo);
                mascota.setFoto(foto);
                 
                repositorioMascota.save(mascota);
            }else{
                throw new MyException("No tiene autorización para realizar esos cambios.");
            }
        }else {
            throw new MyException("No existe la mascota solicitada.");
        }
    }
    
    @Transactional
    public void eliminar (String idUsuario,String idMascota) throws MyException{
         Optional<Mascota>respuesta = repositorioMascota.findById(idMascota);
        if (respuesta.isPresent()){
            Mascota mascota = respuesta.get();
            if (mascota.getUsuario().getId().equals(idUsuario)){
                mascota.setBaja(new Date());
                repositorioMascota.save(mascota);
            } else {
                throw new MyException("No existe la mascota solicitada.");
            }
        }else {
            throw new MyException("No existe la mascota solicitada.");
        }
    }
    
    public void validar(String nombre,Sexo sexo) throws MyException{
        if (nombre==null||nombre.isEmpty()){
            throw new MyException("El nombre de la mascota no puede ser nulo.");
        }
    }
}
