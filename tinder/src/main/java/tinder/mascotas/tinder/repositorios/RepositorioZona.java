package tinder.mascotas.tinder.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tinder.mascotas.tinder.entidades.Zona;

@Repository
public interface RepositorioZona extends JpaRepository<Zona,String>{
    
}
