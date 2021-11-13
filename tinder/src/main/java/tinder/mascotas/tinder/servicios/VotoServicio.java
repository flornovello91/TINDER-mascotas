package tinder.mascotas.tinder.servicios;

import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tinder.mascotas.tinder.entidades.Voto;
import tinder.mascotas.tinder.entidades.Mascota;
import tinder.mascotas.tinder.exception.MyException;
import tinder.mascotas.tinder.repositorios.RepositorioMascota;
import tinder.mascotas.tinder.repositorios.RepositorioVoto;

@Service
public class VotoServicio {
    
    @Autowired
    private RepositorioMascota repositorioMascota;
    
    @Autowired
    private RepositorioVoto repositorioVoto;
    
//    @Autowired
//    private NotificacionServicio notificacionServicio;
    
    public void votar (String idUsuario,String idMascota1,String idMascota2) throws MyException{
        Voto voto = new Voto();
        voto.setFechaRespuestaVoto(new Date());
        
        if (idMascota1.equals(idMascota2)){
            throw new MyException ("No puede realizar esta acción.");
        }
        
        Optional<Mascota>respuesta = repositorioMascota.findById(idMascota1);    //BUSCA LA MASCOTA1 POR EL ID
        if (respuesta.isPresent()){
            Mascota mascota1 = respuesta.get();
            if (mascota1.getUsuario().getId().equals(idUsuario)){    //PARA IDENTIFICAR SI EL ID DE LA MASCOTA COINCIDE CON EL DEL USUARIO Y SI COINCIDE SE SETEA EL VOTO
                voto.setMascota1(mascota1);
            }else{
                throw new MyException("No tiene permisos para realizar cambios.");
            }
        }else{
            throw new MyException("No existe una mascota vinculada con ese identificador.");
        }
        
        Optional<Mascota>respuesta2 = repositorioMascota.findById(idMascota2);   //BUSCA LA MASCOTA2 POR EL ID
        if (respuesta2.isPresent()){
            Mascota mascota2 = respuesta2.get();
            voto.setMascota2(mascota2);
//            notificacionServicio.enviarMail("Tu mascota ha sido votada!", "Tinder Mascota", mascota2.getUsuario().getMail());
        }else {
            throw new MyException("No existe una mascota vinculada a ese identificador.");
        }
        
        repositorioVoto.save(voto);
    }
    public void responder(String idUsuario,String idVoto) throws MyException{
        Optional<Voto>respuesta = repositorioVoto.findById(idVoto);
        if(respuesta.isPresent()){
            Voto voto = respuesta.get();
            voto.setFechaRespuestaVoto(new Date());
            if (voto.getMascota2().getId().equals(idUsuario)){
//                notificacionServicio.enviarMail("Tu voto fue correspondido!", "Tinder Mascota", voto.getMascota1().getUsuario().getMail());
                repositorioVoto.save(voto);
            }else{
                throw new MyException("No tiene permiso para realizar la operación.");
            }
        }else{
            throw new MyException("No existe el voto solicitado");
        }
    }
}
