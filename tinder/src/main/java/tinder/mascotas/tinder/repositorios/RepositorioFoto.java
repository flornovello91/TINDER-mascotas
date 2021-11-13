package tinder.mascotas.tinder.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tinder.mascotas.tinder.entidades.Foto;

@Repository
public interface RepositorioFoto extends JpaRepository<Foto,String> {
    
}
