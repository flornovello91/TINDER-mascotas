
package tinder.mascotas.tinder.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Voto {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVoto;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRespuestaVoto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaVoto() {
        return fechaVoto;
    }

    public void setFechaVoto(Date fechaVoto) {
        this.fechaVoto = fechaVoto;
    }

    public Date getFechaRespuestaVoto() {
        return fechaRespuestaVoto;
    }

    public void setFechaRespuestaVoto(Date fechaRespuestaVoto) {
        this.fechaRespuestaVoto = fechaRespuestaVoto;
    }
    
}
