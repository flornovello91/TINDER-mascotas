package tinder.mascotas.tinder.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tinder.mascotas.tinder.entidades.Usuario;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario,String>{
    
    @Query("SELECT c FROM Usuario c WHERE c.mail = :mail")
    public Usuario BuscarPorMail (@Param("mail")String mail);
}
