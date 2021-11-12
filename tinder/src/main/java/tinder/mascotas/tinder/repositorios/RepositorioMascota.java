
package tinder.mascotas.tinder.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tinder.mascotas.tinder.entidades.Mascota;

@Repository
public interface RepositorioMascota extends JpaRepository<Mascota,String> {
    @Query("SELECT c FROM Mascota c WHERE c.usuario.id = :id")
    public List<Mascota>buscarMascotasPorUsuario(@Param("id") String id );
}
