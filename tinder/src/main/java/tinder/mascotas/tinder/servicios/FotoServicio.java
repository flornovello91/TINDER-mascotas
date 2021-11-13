package tinder.mascotas.tinder.servicios;

import java.io.IOException;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import tinder.mascotas.tinder.entidades.Foto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tinder.mascotas.tinder.exception.MyException;
import tinder.mascotas.tinder.repositorios.RepositorioFoto;

@Service
public class FotoServicio {
    
    @Autowired
    private RepositorioFoto repositorioFoto;
    
    @Transactional     //la notacion transactional es para que el metodo se ejecuta sin lanzar excepciones entonces hace un commit a la base de datos y se aplican todos los cambios.si hubiese algun error, se hace un rollback y no se aplica nada
    public Foto guardar(MultipartFile archivo) throws MyException, IOException{
        if (archivo!=null){
            Foto foto = new Foto();
            foto.setMime(archivo.getContentType());
            foto.setNombre(archivo.getName());
            foto.setContenido(archivo.getBytes());
            
            return repositorioFoto.save(foto);
        }
        return null;
    }
    
    @Transactional
    public Foto actualizar(String idFoto,MultipartFile archivo) throws IOException{
        if (archivo!=null){
            Foto foto = new Foto();
            
            if (idFoto != null){
                Optional<Foto>respuesta= repositorioFoto.findById(idFoto);
                if (respuesta.isPresent()){
                    foto = respuesta.get();
                }
            }
            
            foto.setMime(archivo.getContentType());
            foto.setNombre(archivo.getName());
            foto.setContenido(archivo.getBytes());
            
            return repositorioFoto.save(foto);
        }
        return null;
    }
}
