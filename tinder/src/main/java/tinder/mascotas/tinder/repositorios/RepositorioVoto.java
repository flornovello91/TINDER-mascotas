package tinder.mascotas.tinder.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tinder.mascotas.tinder.entidades.Voto;

@Repository
public interface RepositorioVoto extends JpaRepository<Voto,String>{
    
    @Query("SELECT c FROM Voto c WHERE c.mascota1.id = :id ORDER BY c.fechaVoto DESC")
    public List<Voto> BuscarVotosPropios(@Param("id") String id);
            
    @Query("SELECT c FROM Voto c WHERE c.mascota2.id = :id ORDER BY c.fechaRespuestaVoto DESC")
    public List<Voto> BuscarVotosRecibidos(@Param("id") String id);
}
