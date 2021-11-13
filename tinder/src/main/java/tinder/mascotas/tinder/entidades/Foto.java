package tinder.mascotas.tinder.entidades;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Foto {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    
    private String nombre;
    private String mime;
    
    //LA ANOTACIÓN LOB LE INDICA AL MOTOR DE PERSISTENCIA QUE ES UN TIPO DE DATO GRANDE
    //LA ANOTACIÓN BASIC LE INDICA QUE CARGUE EL CONTENIDO SOLO CUANDO SE LO PIDAMOS HACIENDO QUE LOS QUERYS SEAN MAS LIVIANOS
    //cuando consulte por la foto solo va a traer los atributos que estan marcados como viber (osea los otros comunes)y mediante un método get traigo a los lazy
    @Lob @Basic(fetch=FetchType.LAZY)   
    private byte[] contenido;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }
    
}
